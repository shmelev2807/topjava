package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;

public class MealRepositoryImpl implements MealRepository{
    private static MealRepositoryImpl instance;
    private static int counter = 0;
    private static Map<Integer, Meal> meals;

    public static MealRepositoryImpl getInstance() {
        if (instance == null) {
            instance = new MealRepositoryImpl();
            instance.init();
        }
        return instance;
    }

    private MealRepositoryImpl() {
    }

    private void init(){
        meals = new HashMap<>();
        instance.save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500));
        instance.save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000));
        instance.save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500));
        instance.save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100));
        instance.save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000));
        instance.save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500));
        instance.save(new Meal(0, LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410));
    }

    @Override
    public List<Meal> getAll() {
        return new ArrayList<>(meals.values());
    }

    @Override
    public void save(Meal meal) {
        int id = meal.getId();
        if (id == 0) {
            id = ++counter;
            meal.setId(id);
        }
        meals.put(id, meal);
    }

    @Override
    public void deleteById(int id) {
        meals.remove(id);
    }

    @Override
    public Meal getById(int id) {
        return meals.get(id);
    }

}
