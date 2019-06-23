package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@Controller
public class MealRestController {

    private static final Logger log = LoggerFactory.getLogger(MealRestController.class);

    @Autowired
    private MealService service;

    public Meal get(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("get meal {} for user {}", id, userId);
        return service.get(id, userId);
    }

    public void delete(int id) {
        int userId = SecurityUtil.authUserId();
        log.info("delete meal {} for user {}", id, userId);
        service.delete(id, userId);
    }

    public List<MealTo> getAll() {
        int userId = SecurityUtil.authUserId();
        log.info("getAll for user {}", userId);
        return MealsUtil.getFilteredWithExcess(service.getAll(userId), SecurityUtil.authUserCaloriesPerDay(), LocalTime.MAX, LocalTime.MIN);
    }

    public void deleteAll() {
        int userId = SecurityUtil.authUserId();
        log.info("deleteAll for user {}", userId);
        service.deleteAll(userId);
    }

    public Meal update(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("update {} for user {}", meal, userId);
        return service.update(meal, userId);
    }

    public Meal create(Meal meal) {
        int userId = SecurityUtil.authUserId();
        log.info("create {} for user {}", meal, userId);
        return service.save(meal, userId);
    }

    public List<MealTo> getBetween(LocalDate startDate, LocalTime startTime, LocalDate endDate, LocalTime endTime) {
        int userId = SecurityUtil.authUserId();
        log.info("getBetween dates {} - {} for time {} - {} for user {}", startDate, endDate, startTime, endTime, userId);
        return MealsUtil.getFilteredWithExcess(service.getBetweenDates(startDate, endDate, userId), SecurityUtil.authUserCaloriesPerDay(), startTime, endTime);
    }

}