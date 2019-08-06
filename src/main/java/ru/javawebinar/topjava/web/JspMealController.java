package ru.javawebinar.topjava.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.web.meal.AbstractMealController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;

import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalDate;
import static ru.javawebinar.topjava.util.DateTimeUtil.parseLocalTime;

@Controller
@RequestMapping("/meals")
public class JspMealController extends AbstractMealController {
    @PostMapping()
    public String create(@RequestParam(value = "dateTime") String dateTime,
                         @RequestParam(value = "description") String description,
                         @RequestParam(value = "calories") String calories,
                         @RequestParam(value = "id") String id) {
        Meal meal = new Meal(
                LocalDateTime.parse(dateTime),
                description,
                Integer.parseInt(calories));

        if (StringUtils.isEmpty(id)) {
            super.create(meal);
        } else {
            super.update(meal, Integer.parseInt(id));
        }
        return "redirect:/meals";
    }

    @GetMapping()
    public String getAll(Model model) {
        model.addAttribute("meals", super.getAll());
        return "meals";
    }

    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") String id) {
        super.delete(Integer.parseInt(id));
        return "redirect:/meals";
    }

    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") String id, Model model) {
        Meal meal = super.get(Integer.parseInt(id));
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @GetMapping("/create")
    public String create(Model model) {
        Meal meal = new Meal(LocalDateTime.now().truncatedTo(ChronoUnit.MINUTES), "", 1000);
        model.addAttribute("meal", meal);
        return "mealForm";
    }

    @PostMapping("/filter")
    public String filter(@RequestParam(value = "startDate") String sDate,
                         @RequestParam(value = "endDate") String eDate,
                         @RequestParam(value = "startTime") String sTime,
                         @RequestParam(value = "endTime") String eTime,
                         Model model) {
        LocalDate startDate = parseLocalDate(sDate);
        LocalDate endDate = parseLocalDate(eDate);
        LocalTime startTime = parseLocalTime(sTime);
        LocalTime endTime = parseLocalTime(eTime);
        model.addAttribute("meals", super.getBetween(startDate, startTime, endDate, endTime));
        return "meals";
    }

}
