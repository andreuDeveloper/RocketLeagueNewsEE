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

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet GetFirstNewsServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet GetFirstNewsServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        List<News> ln = newsFacade.findLatestNews(4);
        

        //Remove tags and format
        for (News n : ln) {
            
            String title = Jsoup.parse(n.getTitle()).text().toUpperCase();
            String description = Jsoup.parse(n.getDescription()).text();
            

            if (title.length() > 30) {
                n.setTitle(title.substring(0, 30) + "...");
            } else {
                n.setTitle(title);
            }

            if (description.length() > 150) {
                n.setDescription(description.substring(0, 150) + "...");
            } else {
                n.setDescription(description);
            }
            
            System.out.println("FINAL:" +n.getDescription());
        }
        System.out.println("TAMAÑOOOOOOOOOOOO: " + ln.size());
        request.setAttribute("firstNews", ln);

        RequestDispatcher rd = request.getRequestDispatcher("/index.jsp");
        rd.forward(request, response);

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
