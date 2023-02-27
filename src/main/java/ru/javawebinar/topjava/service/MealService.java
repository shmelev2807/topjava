package ru.javawebinar.topjava.service;

import org.springframework.lang.Nullable;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import static ru.javawebinar.topjava.util.ValidationUtil.*;

@Service
public class MealService {

    private final MealRepository repository;

    public MealService(MealRepository repository) {
        this.repository = repository;
    }

    public List<MealTo> getAll(int userId) {
        return MealsUtil.getTos(repository.getAll(userId), SecurityUtil.authUserCaloriesPerDay());
    }

    public Meal get(int id, int userId) {
        return repository.get(id, userId);
    }

    public MealTo create(MealTo mealTo, int userId) {
        Meal meal = repository.save(MealsUtil.create(mealTo), userId);
        return MealsUtil.createTo(meal, false);
    }

    public void update(MealTo mealTo, int id, int userId) {
        Meal meal = MealsUtil.create(mealTo);
        checkNotFoundWithId(repository.save(meal, userId), id);
    }

    public void delete(int id, int userId) {
        Meal meal = repository.get(id, userId);
        checkNotFoundWithId(meal, id);
        repository.delete(id, userId);
    }

    public List<Meal> getBetweenHalfOpen(@Nullable LocalDate startDate, @Nullable LocalDate endDate, int userId) {
        return repository.getBetweenHalfOpen(DateTimeUtil.getStartInclusive(startDate), DateTimeUtil.getEndExclusive(endDate), userId);
    }



}