/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.rocket.entities.News;
import com.rocket.session.NewsFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;

/**
 *
 * @author Andreu
 */
public class GetMoreNews extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int numberOfNews = Integer.parseInt(request.getParameter("numberOfNews"));
        int latestId = Integer.parseInt(request.getParameter("latestId"));
        try {
            List<News> ln = newsFacade.findPreviousNews(latestId, numberOfNews);
            System.out.println("LISTA CARGADA:" + ln.size());

            if (ln.size() > 0) {
                removeTagsAndFormat(ln);
                Gson gson = new GsonBuilder().setDateFormat("EEE MMM d HH:mm:ss zzz YYYY").create();
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(ln));
            } else {
                Map<String, String> mess = new HashMap<>();
                mess.put("mess", "No more news available");
                Gson gson = new GsonBuilder().create();
                response.setContentType("application/json");
                PrintWriter pw = response.getWriter();
                pw.println(gson.toJson(mess));
            }
        } catch (Exception e) {
            Map<String, String> emess = new HashMap<>();
            emess.put("error", "Server error");

            Gson gson = new GsonBuilder().create();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(emess));
        }
    }

    //Remove tags and format
    private void removeTagsAndFormat(List<News> ln) {
        for (News n : ln) {

            String title = Jsoup.parse(n.getTitle()).text().toUpperCase();
            String description = Jsoup.parse(n.getDescription()).text();

            if (title.length() > 25) {
                n.setTitle(title.substring(0, 25) + "...");
            } else {
                n.setTitle(title);
            }

            if (description.length() > 150) {
                n.setDescription(description.substring(0, 150) + "...");
            } else {
                n.setDescription(description);
            }

            System.out.println("FINAL:" + n.getDescription());
        }
    }
}
