package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.service.MealServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class MealServlet extends HttpServlet {
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEAL = "/meals.jsp";
    private final MealServiceImpl mealService = new MealServiceImpl();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");
        if (action == null) {
            forward = LIST_MEAL;
            request.setAttribute("meals", mealService.getAll());
        } else if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            mealService.deleteById(mealId);
            forward = LIST_MEAL;
            request.setAttribute("meals", mealService.getAll());
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("mealId"));
            MealTo mealTo = mealService.getById(mealId);
            request.setAttribute("meal", mealTo);
        } else {
            forward = LIST_MEAL;
            request.setAttribute("meals", mealService.getAll());
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String mealId = request.getParameter("id");
        int id;
        if(mealId == null || mealId.isEmpty()) {
            id = 0;
        } else {
            id = Integer.parseInt(mealId);
        }
        MealTo mealTo = new MealTo(
                id,
                LocalDateTime.parse(request.getParameter("dateTime"), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                request.getParameter("description"),
                Integer.parseInt(request.getParameter("calories")),
                false
        );
        mealService.save(mealTo);
        request.setAttribute("meals", mealService.getAll());
        request.getRequestDispatcher(LIST_MEAL).forward(request, response);
    }
}
