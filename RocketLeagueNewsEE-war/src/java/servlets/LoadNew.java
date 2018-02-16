/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import com.rocket.entities.News;
import com.rocket.session.NewsFacade;
import java.io.IOException;
import java.io.PrintWriter;
import javax.ejb.EJB;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andreu
 */
public class LoadNew extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            String slugURL = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1);
            System.out.println("SLUG: " + slugURL);

            if (newsFacade.slugExists(slugURL)) {
                News n = newsFacade.findNewBySlug(slugURL);
                System.out.println("Noticia detectada");
                request.setAttribute("new", n);

                RequestDispatcher rd = request.getRequestDispatcher("/new.jsp");
                rd.forward(request, response);
            } else {
                System.out.println("SLUG: Not found");
                response.sendError(HttpServletResponse.SC_NOT_FOUND);
            }
            //Redirect to 404
        } catch (Exception e) {
            //Excpetion in the server
            response.sendError(HttpServletResponse.SC_NOT_FOUND);
            System.out.println("ERROR: " + e.getMessage());
        }

    }

}
