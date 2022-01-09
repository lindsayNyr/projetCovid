package fr.ul.projetcovid.servlets;

import fr.ul.projetcovid.persistence.MyActivity;
import fr.ul.projetcovid.persistence.Place;
import fr.ul.projetcovid.persistence.dao.MyActivityDAO;
import fr.ul.projetcovid.persistence.dao.PlaceDAO;
import javaf.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "deletePlace", value = "/deletePlace")
public class DeletePlaceServlet extends HttpServlet {

    private final PlaceDAO placeDAO = new PlaceDAO();
    private final MyActivityDAO myActivityDAO = new MyActivityDAO();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String id = Objects.nonNullOrElse(request.getParameter("idPlace"), "");


        Place place = placeDAO.getById(id);

        if(place == null){
            request.setAttribute("error", "Le lieux n'existe pas");
            this.getServletContext().getRequestDispatcher("/place.jsp").forward(request, response);
        }




        List<MyActivity> myActivityList = myActivityDAO.getAll();
        for(MyActivity m : myActivityList){
            if(m.getPlace().getId().equals(place.getId())){
                myActivityDAO.remove(m);
            }
        }

        placeDAO.remove(place);

        response.sendRedirect(getServletContext().getContextPath() + "/place.jsp");



    }






}