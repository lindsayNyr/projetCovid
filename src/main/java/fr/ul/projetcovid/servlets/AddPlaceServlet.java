package fr.ul.projetcovid.servlets;


import fr.ul.projetcovid.persistence.Place;
import fr.ul.projetcovid.persistence.dao.PlaceDAO;
import javaf.util.Objects;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.io.IOException;
import java.util.Set;

@WebServlet(name = "addPlacePOST", value = "/addPlace")
public class AddPlaceServlet extends HttpServlet {

    PlaceDAO placeDAO = new PlaceDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        final String city = Objects.nonNullOrElse(request.getParameter("ville"), "");
        final String codePostal = Objects.nonNullOrElse(request.getParameter("cp"), "");
        final String adresse = Objects.nonNullOrElse(request.getParameter("adresse"), "");
        final String name = Objects.nonNullOrElse(request.getParameter("name"), "");

        Place place = new Place();

        final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();
        final Set<ConstraintViolation<Place>> violations = validator.validate(place);
        place.setName(name);
        place.setCodePostal(codePostal);
        place.setAdresse(adresse);
        place.setCity(city);
        placeDAO.save(place);


        if (!violations.isEmpty()) {
            StringBuilder sb = new StringBuilder();
            for (final ConstraintViolation<Place> violation : violations)
                sb.append(violation.getMessage()).append("\n");
            request.setAttribute("error", sb.toString());
            getServletContext().getRequestDispatcher("/place.jsp").forward(request, response);
            return;
        }


        response.sendRedirect(this.getServletContext().getContextPath() + "/place.jsp");

    }

}
