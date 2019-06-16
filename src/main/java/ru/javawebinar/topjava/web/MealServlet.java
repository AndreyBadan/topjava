package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.InMemoryMealRepository;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.time.LocalDateTime;


import static org.slf4j.LoggerFactory.getLogger;

public class MealServlet extends HttpServlet {
    private static final Logger log = getLogger(MealServlet.class);

    private MealRepository repository;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        repository = new InMemoryMealRepository();
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        String id = req.getParameter("id");

        Meal meal = new Meal(id.isEmpty() ? null : Integer.parseInt(id),
                LocalDateTime.parse(req.getParameter("dateTime")),
                req.getParameter("description"),
                Integer.parseInt(req.getParameter("calories")));

        log.debug(meal.isNew() ? "create {}" : "update {}", meal);
        repository.save(meal);
        resp.sendRedirect("meals");

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action == null) {
            log.debug("getAll");

            req.setAttribute("listMealTo",
                    MealsUtil.getWithExcess(repository.getAll(), 2000));
            req.getRequestDispatcher("/meals.jsp").forward(req, resp);
        } else if (action.equals("delete")) {
            int id = Integer.parseInt(req.getParameter("id"));
            log.debug("Delete {}", id);

            repository.delete(id);
            resp.sendRedirect("meals");
        } else {
            log.debug("create or update");
            final Meal meal = action.equals("create") ?
                    new Meal(LocalDateTime.now(), "", 1000) :
                    repository.get(Integer.valueOf(req.getParameter("id")));

            req.setAttribute("meal", meal);
            req.getRequestDispatcher("mealEdit.jsp").forward(req, resp);
        }
    }

}
