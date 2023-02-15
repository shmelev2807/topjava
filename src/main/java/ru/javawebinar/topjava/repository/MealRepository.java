package ru.javawebinar.topjava.repository;

import ru.javawebinar.topjava.model.Meal;

import java.util.ArrayList;
import java.util.List;

public interface MealRepository {
    public List<Meal> getAll();


    public void save(Meal meal);


    public void deleteById(int id);

    public Meal getById(int id);
}
