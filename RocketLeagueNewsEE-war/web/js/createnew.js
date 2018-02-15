/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var tinymce;
$(document).ready(function () {

    initTextArea();
    $("#formNew").submit(function () {
        if (tinymce.activeEditor.getContent().length > 0) {
            createNewPost();
        } else {
            showToast("Insert a new's content", "", "warning", "#3366ff");
        }
        return false;
    });
});
function initTextArea() {
    tinymce.init({
        selector: 'textarea',
        height: 200,
        max_height: 350,
        menubar: true,
        entity_encoding: "raw",
        encoding: "UTF-8",
        plugins: [
            'advlist autolink lists link charmap print preview anchor textcolor',
            'searchreplace visualblocks code',
            'insertdatetime media contextmenu paste code help wordcount'
        ],
        toolbar: 'insert | undo redo |  formatselect | bold italic backcolor  | alignleft aligncenter alignright alignjustify | bullist numlist outdent indent | removeformat | help',
        content_css: [
            '//fonts.googleapis.com/css?family=Lato:300,300i,400,400i',
            '//www.tinymce.com/css/codepen.min.css']
    });
}


function createNewPost() {
    $("#divCargando").fadeIn(400);
    var url = "CreateNewServlet";

    $("#btnLoad").prop("disabled", true); // deshabilitar enviar, evita doble post.

    // Get form
    var form = $('#formNew')[0];
    // Create an FormData object
    var data = new FormData(form);

    $.ajax({
        type: "POST",
        enctype: 'multipart/form-data',
        url: url,
        data: data,
        processData: false,
        contentType: false,
        cache: false,
        success: function (rsp) {
            showToast("Successfull", rsp["mess"], "success", "#36B62D");
            $("#divCargando").fadeOut(400);
        },
        error: function (e) {
            $("#divCargando").fadeOut(400);
            if (e["responseJSON"] === undefined)
                showToast("UNKNOWN ERROR", "Try it later", "error", "#D43721");
            else
                showToast(e["responseJSON"]["error"], "Whoops!", "error", "#D43721");
        }
    });
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
        hideAfter: 3000, // false to make it sticky or number representing the miliseconds as time after which toast needs to be hidden
        position: 'top-center', // bottom-left or bottom-right or bottom-center or top-left or top-right or top-center or mid-center or an object representing the left, right, top, bottom values
        textAlign: 'left', // Text alignment i.e. left, right or center
        loader: true, // Whether to show loader or not. True by default
        loaderBg: '#9EC600', // Background color of the toast loader
        bgColor: bgColor
    });
}