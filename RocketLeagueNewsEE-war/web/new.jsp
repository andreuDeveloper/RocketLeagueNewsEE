<%-- 
    Document   : new
    Created on : 13-feb-2018, 11:52:25
    Author     : Andreu
--%>

<%@page import="com.rocket.entities.News"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>


<% News n = (News) request.getAttribute("new");%>

<!DOCTYPE html>
<html>
    <head>
        <title>Rocket League News</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
        <link rel="icon" href="../img/icons/ico.ico" type="image/x-icon" />
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.1.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="../css/styleNew.css">
        <link href="https://fonts.googleapis.com/css?family=Aldrich" rel="stylesheet">

        <script src="/RocketLeagueNewsEE-war/js/jquery.toast.min.js"></script>
        <link rel="stylesheet" type="text/css" href="/RocketLeagueNewsEE-war/css/jquery.toast.min.css" />
    </head>


    <body>

        <div id="fb-root"></div>
        <script>(function (d, s, id) {
                var js, fjs = d.getElementsByTagName(s)[0];
                if (d.getElementById(id))
                    return;
                js = d.createElement(s);
                js.id = id;
                js.src = "//connect.facebook.net/es_ES/sdk.js#xfbml=1&version=v2.9";
                fjs.parentNode.insertBefore(js, fjs);
            }(document, 'script', 'facebook-jssdk'));</script>

        <div id="jumb" class="jumbotron text-center">
            <h1>ROCKET LEAGUE NEWS</h1>
        </div>


        <nav id="mybar" class="navbar navbar-inverse">
            <div class="container-fluid">
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>                        
                    </button>
                    <a class="navbar-brand active" href="../"><span class="glyphicon glyphicon-home"></span></a>
                </div>
                <div class="collapse navbar-collapse" id="myNavbar">
                    <ul class="nav navbar-nav">
                        <li class="#"><a href="#">Competitive</a></li>
                        <li><a href="#">Community</a></li>
                        <li><a href="#">Workshop</a></li>
                        <li class="dropdown">
                            <a class="dropdown-toggle" data-toggle="dropdown" href="#">Patches<span class="caret"></span></a>
                            <ul class="dropdown-menu">
                                <li><a href="#" class="dr">Patch 1.34</a></li>
                                <li><a href="#" class="dr">Patch 1.33</a></li>
                                <li><a href="#" class="dr">Patch 1.32</a></li>
                                <li><a href="#" class="dr">Patch 1.31</a></li>
                                <li><a href="#" class="dr">Patch 1.30</a></li>
                            </ul>
                        </li>
                        <li class="#"><a href="../createnew.html">Create New</a></li>
                    </ul>
                    <form class="navbar-form navbar-right" id="search">
                        <div class="input-group">
                            <input type="text" class="form-control" placeholder="Search">
                            <div class="input-group-btn">
                                <button class="btn btn-default" type="submit">
                                    <i class="glyphicon glyphicon-search"></i>
                                </button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </nav>

        <div id="mainC">

            <div id="publicidad">
                <a href="publ.html"><img id="pubV" class="img-responsive center-block" src="../img/ads/1h.jpg" alt="pub ver img"></a>
                <a href="publ.html">
                    <picture>
                        <source srcset="../img/ads/1m.png" media="(min-width: 650px)">
                        <source srcset="../img/ads/2m.png" media="(min-width: 500px)">
                        <source srcset="../img/ads/3m.png" media="(min-width: 400px)">

                        <img id="pubH" class="img-responsive center-block" src="../img/ads/4m.png" alt="pub hor img">
                    </picture>
                </a>
                <iframe id="fbTime" src="https://www.facebook.com/plugins/page.php?href=https%3A%2F%2Fwww.facebook.com%2FRocketLeague%2F&tabs=timeline&width=300&height=800&small_header=false&adapt_container_width=false&hide_cover=false&show_facepile=false&appId"
                        width="300" height="800" style="border:none;overflow:hidden"></iframe>
            </div>

            <div id="noticias">
                <div id="c" class="container">
                    <h3 class="title"><%=n.getTitle()%></h3>
                    <h5 class="date"><%=n.getDate()%> <span> - (<%=n.getUsername()%>)</span></h5>
                    <div id="c2" class="text-justify">
                        <picture>
                            <source srcset="../img/uploads/<%=n.getId()%>.png" media="(max-width: 400px)">
                            <source srcset="../img/uploads/<%=n.getId()%>.png">
                            <img class="imgN img-responsive center-block" src="../img/uploads/<%=n.getId()%>.png" alt="dropshotIMG">
                        </picture>

                        <div id="descriptionNew">
                            <%=n.getDescription()%>
                        </div>
                    </div>
                </div>
            </div>
        </div>



        <div class="footer">

            <ul class="icons">
                <li id="fb">
                    <a href="https://www.facebook.com/RocketLeague/"><img class="img-responsive center-block" src="../img/icons/facebook.png" alt="fb logo" /></a>
                </li>
                <li id="tw">
                    <a href="https://twitter.com/rocketleague?lang=es"><img class="img-responsive center-block" src="../img/icons/twitter.png" alt="tw logo" /></a>
                </li>
                <li id="yb">
                    <a href="https://www.youtube.com/user/RocketLeagueGame"><img class="img-responsive center-block" src="../img/icons/youtube.png" alt="yb logo" /></a>
                </li>
                <li id="gg">
                    <a href="https://plus.google.com/communities/100086543914019394793"><img class="img-responsive center-block" src="../img/icons/google.png" alt="gg logo" /></a>
                </li>
            </ul>

            <ul class="about">
                <li>DESIGNER: Andreu Juan Ferrá</li>
                <li>DEVELOPED: Andreu Juan Ferrá</li>
                <li>BASED ON the oficial game Rocket League</li>
                <li>Icons made by <a href="http://www.freepik.com" title="Freepik">Freepik</a> from <a href="http://www.flaticon.com"
                                                                                                       title="Flaticon">www.flaticon.com</a> is licensed by <a href="http://creativecommons.org/licenses/by/3.0/"
                                                                                                       title="Creative Commons BY 3.0" target="_blank">CC 3.0 BY</a></li>
            </ul>
        </div>
    </body>
</html>