package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.web.meal.MealRestController;
import ru.javawebinar.topjava.web.user.AdminRestController;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class SpringMain {
    public static void main(String[] args) {
        // java 7 automatic resource management (ARM)
        try (ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml")) {
            System.out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));
            AdminRestController adminUserController = appCtx.getBean(AdminRestController.class);
            adminUserController.create(new User(null, "userName", "email@mail.ru", "password", Role.ADMIN));

            MealRestController mealRestController = appCtx.getBean(MealRestController.class);
            System.out.println(mealRestController.getAll());
            MealTo mealTo = new MealTo(null,
                    LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0),
                    "Новая еда",
                    750,
                    false);
            mealRestController.create(mealTo);
            System.out.println(mealRestController.get(8));
            List<MealTo> filteredMealsWithExceed = mealRestController.getBetweenHalfOpen(
                    LocalDate.of(2020, Month.JANUARY, 30),
                    LocalTime.of(7, 0),
                    LocalDate.of(2020, Month.JANUARY, 31),
                    LocalTime.of(11, 0)
            );
            filteredMealsWithExceed.forEach(System.out::println);
            System.out.println();
            System.out.println(mealRestController.getBetweenHalfOpen(null, null, null, null));

        }
    }
}
