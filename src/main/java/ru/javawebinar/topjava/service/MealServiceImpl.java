package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.MealRepositoryImpl;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.List;

public class MealServiceImpl implements MealService{
    public static final int CALORIES_LIMIT = 2000;

    private final MealRepository mealRepository;

    public MealServiceImpl() {
        mealRepository = MealRepositoryImpl.getInstance();
    }

    @Override
    public List<MealTo> getAll() {
        return MealsUtil.filteredByStreams(mealRepository.getAll(), LocalTime.MIN, LocalTime.MAX, CALORIES_LIMIT);
    }

    @Override
    public void save(MealTo mealTo) {
        mealRepository.save(MealsUtil.create(mealTo));
    }

    @Override
    public void deleteById(int id) {
        mealRepository.deleteById(id);
    }

    @Override
    public MealTo getById(int id) {
        return MealsUtil.createTo(mealRepository.getById(id), true);
    }
}

