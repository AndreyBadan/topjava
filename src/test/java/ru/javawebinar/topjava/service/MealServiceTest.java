package ru.javawebinar.topjava.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import static org.junit.Assert.*;
import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.MealTestData.MEAL;
import static ru.javawebinar.topjava.MealTestData.MEALS;
import static ru.javawebinar.topjava.UserTestData.ADMIN_ID;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class MealServiceTest {

    @Autowired
    MealService mealService;

    @Test
    public void get() {
        Meal meal = mealService.get(100002, USER_ID);
        assertEquals(MEAL, meal);
    }

    @Test(expected = NotFoundException.class)
    public void getNotFound() {
        mealService.get(1, USER_ID);
    }

    @Test
    public void delete() {
        mealService.delete(100008, ADMIN_ID);
        assertThat(mealService.getAll(ADMIN_ID)).isEqualTo(MEALS);
    }

    @Test
    public void getBetweenDates() {
    }

    @Test
    public void getAll() {
    }

    @Test
    public void update() {
    }

    @Test
    public void create() {
    }
}