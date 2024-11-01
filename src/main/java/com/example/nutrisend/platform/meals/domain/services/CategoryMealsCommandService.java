package com.example.nutrisend.platform.meals.domain.services;

import com.example.nutrisend.platform.meals.domain.model.aggregates.CategoryMeals;
import com.example.nutrisend.platform.meals.domain.model.commands.CreateCategoryMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.DeleteCategoryMealsCommand;
import com.example.nutrisend.platform.meals.domain.model.commands.UpdateCategoryMealsCommand;

import java.util.Optional;

public interface CategoryMealsCommandService {
    Optional<CategoryMeals> handle(CreateCategoryMealsCommand command);
    void handle(DeleteCategoryMealsCommand command);
    Optional<CategoryMeals> handle(UpdateCategoryMealsCommand command);
}
