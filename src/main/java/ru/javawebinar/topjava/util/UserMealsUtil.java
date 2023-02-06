package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByCycles(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
        System.out.println(LocalDateTime.now());
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        List<UserMealWithExcess> mealsWithExcess = new ArrayList<>(meals.size());
        Map<LocalDate, Integer> caloriesInDate = new HashMap<>();
        List<UserMeal> mealsWithTimeInRange = new ArrayList<>();

        for (UserMeal userMeal : meals) {
            LocalDateTime userMealDateTime = userMeal.getDateTime();
            LocalDate userMealDate = userMeal.getDateTime().toLocalDate();
            LocalTime userMealTime = userMealDateTime.toLocalTime();
            int caloriesInMeal = userMeal.getCalories();

            caloriesInDate.merge(userMealDate, caloriesInMeal, Integer::sum);

            if (TimeUtil.isBetweenHalfOpen(userMealTime, startTime, endTime)) {
                mealsWithTimeInRange.add(userMeal);

            }
        }

        for (UserMeal userMeal : mealsWithTimeInRange) {
            LocalDateTime userMealDateTime = userMeal.getDateTime();
            LocalDate userMealDate = userMeal.getDateTime().toLocalDate();
            int caloriesInMeal = userMeal.getCalories();
            boolean caloriesIsExcess = caloriesInDate.get(userMealDate) > caloriesPerDay;

            UserMealWithExcess userMealWithExcess =
                    new UserMealWithExcess(userMealDateTime,
                            userMeal.getDescription(),
                            caloriesInMeal,
                            caloriesIsExcess);
            mealsWithExcess.add(userMealWithExcess);
        }

        return mealsWithExcess;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {

        Map<LocalDate, Integer> caloriesSumPerDate = meals.stream()
                .collect(
                        Collectors.groupingBy(UserMeal::getDate, Collectors.summingInt(UserMeal::getCalories)));
//                        Collectors.toMap(UserMeal::getDate, UserMeal::getCalories, Integer::sum));
        return meals.stream().filter(m -> TimeUtil.isBetweenHalfOpen(m.getDateTime().toLocalTime(), startTime, endTime))
                .map(m -> toUserMealWithExcess(m, caloriesSumPerDate.get(m.getDate()) > caloriesPerDay))
                .collect(Collectors.toList());
    }

    public static UserMealWithExcess toUserMealWithExcess(UserMeal userMeal, boolean excess) {
        return new UserMealWithExcess(
                userMeal.getDateTime(),
                userMeal.getDescription(),
                userMeal.getCalories(),
                excess);
    }
}
