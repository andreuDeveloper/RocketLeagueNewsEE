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
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Andreu
 */
public class CreateNewServlet extends HttpServlet {

    @EJB
    private NewsFacade newsFacade;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            String username = request.getRemoteUser();
            String title = request.getParameter("title");
            String description = request.getParameter("description");

            //String img = request.getParameter("image"); ¿?¿?¿?¿ wtf
            News n = new News();
            n.setTitle(title);
            n.setDescription(description);
            n.setUsername(username);
            
            Date date = new Date();
            n.setDate(date);
            newsFacade.create(n);

            Map<String, String> mess = new HashMap<>();
            mess.put("mess", "New Addedd");

            Gson gson = new GsonBuilder().create();
            response.setContentType("application/json");
            PrintWriter pw = response.getWriter();
            pw.println(gson.toJson(mess));

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

}
