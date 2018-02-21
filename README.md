# RocketLeagueNewsEE
Rocket League News - Using Java Enterprice Edition

* By Andreu Juan Ferrá
* Data access
* DAM2


PROJECT
--------------
Recyle the code of the web page of news of the last year (DAM 1) to achieve the next requirements:
* Do a Relational DB in PostgreSQL
* Codify the components EJB in a Java EE aplication project
* Do a servlet  to see the latest news (using JSP)
* Modify the js to get news and put it in the html (jsp) at the scroll / button click
* Do a form to insert news
* Servlet do post to send the new that must be added, using messages to notify the success / error, optional, using TinyMCE
* Use the rolos of JavaEE with basic authentication
* Only the admin role can add news

Use Java EE, EJB, PostgreSQL, JQuery, CSS and Boostrap (obligatory)


EVALUATION
-------------------------
* **Use Java EE with EJB and PostgreSQL**
  * The project is done with Java Enterprise Edition
  * Use EJB linked to the PostgreSQL table of news
    * Using glassfish

* **Load 4 news with .jsp in the main web page**
  * In my case I load 4 news instead of 3, because I show my news, in rows, with 2 columns each row, where each of them have a new. So, is more esthetic show 4
  * This works with a call to a Servlet, and it takes 4 news, and send the news to the .jsp when the servlet redirects to the jsp.
  * The reason of this, is because js code is not preloaded in the html, but .jsp have code that it’s preloaded, so it will be detected by google, and it will help google to find results related.

* **Load more news with the scroll and a button of load more news with a load status**
  * At the bottom of the main web page there are a blue button, that allows charge more news (if there are more news). Also, if it detects the end of the web page, when scrolling, it will automatically load more news (if there are more news available)
  * Each time that detects one of the previous events:
  * A toast will pop up! Saying if the news are loaded successfully, if there was an error, or if there are not more news available.
  * Also a load status will appear in the screen when tries to get more news
  
* **Implemented a web page with a form for insert news**
  * There a section to upload news, this section is restricted to admin users only, so when we click in the section “Create New” it will pop up a login.
  * Only the users that are in the group-rol allowed can enter in this site
  * The site have a field to add the title of the new
  * An area to insert the description of the new and a file upload to insert the image of the new
  
* **Front-end.war and Back-end.war**
  * This doble war is not implemented




CHANGELOG
---------
* **C-01**
  * Update of the base project "Web Noticias" of DAM1, but in this case with Java EE
  * Creation of the database in postgreSQL
  * Configuration of the glassfish
  * Bind configuration Java EE - Glassfish and generation of entities EJB
* **C-02**
  * Changed index.html -> index.jsp
  * Creation of servlet that add a new
  * Creation of the desing of a web.html to create / add news
  * Limit the role of admin (roles of glassfish) to be the only one with access to the section "Create news"
* **C-03**
  * Insert of the library toast
  * First inserts and creation of news, but only with a title and a description of a textArea
* **C-04**
  * Changed the text area to the TinyMCE
  * Added nice clean file input plugin to upload images
  * (The uploads not work)
* **C-05**
  * Solved the upload problems of the images
  * Inserting news working, with title, description and image
  * Solved problems of UTF-8
  * Added the gif loading
* **C-06**
  * Creation of a servlet to autoget the last 4 news
  * Insert of the news readed by the servlet in the index.jsp
  * Fixed some visual problems
* **C-07**
  * New servlet to charge more news
  * Implementation of an ajax with the servlet to get more news when the scroll reach the bottom of the screen of the button of more news is pushed
  * Added the gif of loading news
* **C-08**
  * New servlet to get an specific new
  * New jsp templete to charge news
  * Implementation between both
* **C-09**
  * Fixes of the visualization, workflows, and more..
