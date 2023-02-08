package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.MealTo;

import java.util.List;

public interface MealService {
    public List<MealTo> getAll();
    public void save(MealTo mealTo);
    public void deleteById(int id);

}
