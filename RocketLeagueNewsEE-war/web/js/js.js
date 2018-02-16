var listIdNews = [];
var alturaJumb;
var alturaBar;

var intervalPub;
var imgPub = ["img/ads/1h.jpg", "img/ads/2h.jpg", "img/ads/3h.jpg"];
var inxPub = 1;

/*Al cargar la pagina*/
$(document).ready(function () {

    //Para evitar problemas con cargas automaticas del Json, al cargar la
    //web se hará un scroll hacia arriba directamente
    $("h1").fadeIn(1500);
    $('html, body').animate({scrollTop: 0}, 400);

    cargarEfectos();
    $(window).resize(function () {
        alturaJumb = $('#jumb').outerHeight();
        cargarEfectos();
    });


    //BOTON DE CARGAR NOTICIAS
    $("#btnLoad").click(function () {
        cargarNoticias();
    });


    alturaJumb = $('#jumb').outerHeight();
    alturaBar = $('#mybar').outerHeight() + 20;

    $(window).scroll(function () {
        if ($(window).scrollTop() + $(window).height() >= $(document).height() - 1) {
            cargarNoticias();
        }
        /*
        if ($(window).scrollTop() > alturaJumb) {
            $('#mybar').css('position', 'fixed').css('top', '0').css('width', '100%');
            $('#mainC').css('margin-top', alturaBar);
        } else {
            $('#mybar').css('position', 'relative');
            $('#mainC').css('margin-top', '20px');
        }
        */

    });

    intervalPub = setInterval(function () {

        if ($(window).width() > 750) {
            $("#pubV").stop(true, true).fadeOut(200);
            $("#pubV").attr("src", imgPub[inxPub % imgPub.length]);
            $("#pubV").stop(true, true).fadeIn(200);
            inxPub++;
        } else {
            $("#pubV").stop(true, true).hide();
        }

    }, 15000);
});


//DEPENDENDO DEL TAMAÑO DE LA PANTALLA HABRÁ UNOS EFECTOS U OTROS
function cargarEfectos() {
    if ($(window).width() < 750) {
        $(".not .desc").stop(true, true).show();

        $(".not").mouseenter(function () {
            $("img", this).stop(true, true).fadeIn(1);
            $(".desc", this).stop(true, true).fadeIn(1);
        });

        $(".not").mouseleave(function () {
            $("img", this).stop(true, true).fadeIn(1);
            $(".desc", this).stop(true, true).fadeIn(1);
        });
    } else {
        $(".not .desc").stop(true, true).hide();
        $(".not").mouseenter(function () {
            $("img", this).stop(true, true).fadeTo(0.5, 0.2);
            $(".desc", this).stop(true, true).fadeIn(200);
        });

        $(".not").mouseleave(function () {
            $("img", this).stop(true, true).fadeTo(0.5, 1);
            $(".desc", this).stop(true, true).fadeOut(200)
        });
    }

}

/*ajax POST*/
function cargarNoticias() {


    $("#divCargando").stop(true,true).fadeIn(300);
    var url = "GetMoreNews";
    var numberOfNews = 2;
    var latestId = listIdNews[listIdNews.length - 1];
    showToast(latestId, "Try it later", "error", "#D43721");

    $.ajax({
        method: "POST",
        url: url,
        data: {numberOfNews: numberOfNews, latestId: latestId},
        success: function (rsp) {
            $("#divCargando").stop(true,true).fadeOut(300);
            if (rsp["mess"] === "No more news available") {
                showToast("No more news", "", "warning", "#3366ff");
            } else {
                showToast("Loaded Successfully", "Loaded news", "success", "#36B62D");
                crearNoticia(rsp);
            }
        },
        error: function (e) {
            $("#divCargando").stop(true,true).fadeOut(300);
            if (e["responseJSON"] === undefined)
                showToast("UNKNOWN ERROR", "Try it later", "error", "#D43721");
            else
                showToast(e["responseJSON"]["error"], "Whoops!", "error", "#D43721");
        }
    });
}


function cargarFichero(nombreFichero) {
    var path = "https://raw.githubusercontent.com/SOSandreu1095/WebNoticias/master/data/" + nombreFichero;

    //cargar Noticias
    $.getJSON(path, function (jsonObject) {
        crearNoticia(jsonObject, nombreFichero);
    });
    return true;
}

function crearNoticia(jsn) {

    var m = document.getElementById("notCargar");
    var row = document.createElement("div");

    row.className = "row";
    m.appendChild(row);

    $.each(jsn, function (i, item) {
        listIdNews.push(item.id.toString());

        var col = document.createElement("div");
        col.className = "col col-sm-6";
        var a = document.createElement("a");
        a.setAttribute('href', "#");
        var h3 = document.createElement("h3");
        h3.className = "notTitle";
        h3.textContent = item.title;
        var h5 = document.createElement("h5");
        h5.className = "date";
        h5.textContent = item.date;
        var n = document.createElement("div");
        n.className = "not img-rounded";
        var img = document.createElement("img");
        img.src = "img/uploads/"+item.id+".png";
        img.alt = "image New";
        var des = document.createElement("p");
        des.className = "desc";
        des.textContent = item.description;

        n.appendChild(img);
        n.appendChild(des);
        a.appendChild(h3);
        a.appendChild(h5);
        a.appendChild(n);
        col.appendChild(a);
        row.appendChild(col);

        cargarEfectos();
    });

    /*
     for (i = 0; i < 2; i++) {
     var col = document.createElement("div");
     col.className = "col col-sm-6";
     var a = document.createElement("a");
     a.setAttribute('href', "#");
     var h3 = document.createElement("h3");
     h3.className = "notTitle";
     h3.textContent = json[i].title;
     var h5 = document.createElement("h5");
     h5.className = "date";
     h5.textContent = json[i].date;
     var n = document.createElement("div");
     n.className = "not img-rounded";
     var img = document.createElement("img");
     img.src = "img/not/training.jpg";
     img.alt = "image New";
     var des = document.createElement("p");
     des.className = "desc";
     des.textContent = json[i].description;
     
     n.appendChild(img);
     n.appendChild(des);
     a.appendChild(h3);
     a.appendChild(h5);
     a.appendChild(n);
     col.appendChild(a);
     row.appendChild(col);
     
     cargarEfectos();
     }
     */
}

/**
 * The toast is an external librery developed by https://github.com/kamranahmedse/jquery-toast-plugin/
 * Here there are the documentation about how to use it: http://kamranahmed.info/toast
 * @param {type} head Main text message
 * @param {type} text Submessage
 * @param {type} icon (warning | success | error | info)
 * @param {type} bgColor Color of the toast
 * @returns {undefined}
 */
function showToast(head, text, icon, bgColor) {
    $.toast({
        text: text, // Text that is to be shown in the toast
        heading: head, // Optional heading to be shown on the toast
        icon: icon, // Type of toast icon: warning | success | error | info
        showHideTransition: 'fade', // fade, slide or plain
        allowToastClose: false, // Boolean value true or false
        hideAfter: 2000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
        position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
        textAlign: 'left', // Text alignment i.e. left, right or center
        loader: true, // Whether to show loader or not. True by default
        loaderBg: '#9EC600', // Background color of the toast loader
        bgColor: bgColor
    });
}
