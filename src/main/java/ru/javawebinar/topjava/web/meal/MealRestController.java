package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static ru.javawebinar.topjava.util.ValidationUtil.assureIdConsistent;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNew;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserCaloriesPerDay;
import static ru.javawebinar.topjava.web.SecurityUtil.authUserId;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    private final MealService mealService;

    public MealRestController(MealService mealService) {
        this.mealService = mealService;
    }

    public List<MealTo> getAll() {
        int userId = authUserId();
        log.info("getAll for user {}", userId);
        return mealService.getAll(userId);
    }

    public MealTo get(int id) {
        int userId = authUserId();
        log.info("get meal {} for user {}", id, userId);
        return MealsUtil.createTo(mealService.get(id, userId), false);
    }

    public void create(MealTo mealTo) {
        int userId = authUserId();
        checkNew(mealTo);
        log.info("create meal {} for user {}", mealTo, userId);
        mealService.create(mealTo, userId);
    }

    public void update(MealTo mealTo, int id) {
        int userId = authUserId();
        log.info("update meal {} with id {} for user {}", mealTo, id, userId);
        assureIdConsistent(mealTo, id);
        mealService.update(mealTo, id, userId);
    }

    public void delete(int id) {
        int userId = authUserId();
        log.info("delete meal with id {} for user {}", id, userId);
        mealService.delete(id, userId);
    }

    public List<MealTo> getBetweenHalfOpen(@Nullable LocalDate startDate, @Nullable LocalTime startTime,
                                           @Nullable LocalDate endDate, @Nullable LocalTime endTime) {
        int userId = authUserId();
        log.info("getBetween dates({} - {}) times({} - {}) for user {}", startDate, endDate, startTime, endTime, userId);
        List<Meal> meals = mealService.getBetweenHalfOpen(startDate, endDate, userId);
        return MealsUtil.getFilteredTos(meals, authUserCaloriesPerDay(), startTime, endTime);
    }

}