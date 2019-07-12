package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MealTestData {
    public static final Meal MEAL = new Meal(100002, LocalDateTime.of(2015, 5, 30, 10,0),"Завтрак", 500);

    public static final List<Meal> MEALS = Arrays.asList(
            new Meal(100010, LocalDateTime.of(2015, 6, 21, 20, 0), "Ужин", 510),
            new Meal(100009, LocalDateTime.of(2015, 6, 21, 13, 0), "Обед", 1000)
    );
}
