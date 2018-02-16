/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.google.gson.Gson;
import com.rocket.entities.News;
import com.rocket.session.NewsFacade;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.jsoup.Jsoup;

/**
 *
 * @author Andreu
 */
public class GetFirstNewsServlet extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        List<News> ln = newsFacade.findLatestNews(4);

        removeTagsAndFormat(ln);
        request.setAttribute("firstNews", ln);

        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);

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
        }
    }

}
