package org.yearup.data.mysql;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.yearup.models.Category;
import org.yearup.models.Product;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MySqlCategoryDaoTest extends BaseDaoTestClass{


        private MySqlProductDao dao;

        @BeforeEach
        public void setup()
        {
            dao = new MySqlProductDao(dataSource);
        }

    @Test
    void getAllCategories_shouldReturn_allCategories() {
        // Arrange
        MySqlCategoryDao categoryDAO = new MySqlCategoryDao(dataSource);
        int num = 3;

        // Act
        List<Category> actualCategories = categoryDAO.getAllCategories();

        // Assert
        assertEquals(num, actualCategories.size());

    }


    @Test
    void getById_shouldReturn_theCorrectCategory() {
        // arrange
        int category_Id = 1;
        Category expected = new Category()
        {{
            setCategoryId(1);
            setName("Smartphone");
            setDescription("A powerful and feature-rich smartphone for all your communication needs.");
        }};

        // act
        var actual = dao.getById(category_Id);

        // assert
        assertEquals(expected.getName(), actual.getName(), "Because I tried to get category 1 from the database.");
    }



    @Test
    void create_shouldCreateCategory() {
        // Arrange
        MySqlCategoryDao dao = new MySqlCategoryDao(dataSource);
        Category category = new Category();
        category.setName("Smartphone");
        category.setDescription("A smartphone for all your communication needs.");

        // Act
        dao.create(category);
        Category createdCategory = dao.getById(category.getCategoryId());

        // Assert
        Assertions.assertEquals(category.getName(), createdCategory.getName());
        Assertions.assertEquals(category.getDescription(), createdCategory.getDescription());

    }

    @Test
    void update_shouldUpdateCategory() {
        // Arrange
        int categoryId = 1;
        MySqlCategoryDao dao = new MySqlCategoryDao(dataSource);
        Category categoryToUpdate = dao.getById(categoryId);
        String newDescription = "Updated description";

        // Act
        categoryToUpdate.setDescription(newDescription);
        dao.update(categoryId,categoryToUpdate);
        Category updatedCategory = dao.getById(categoryId);

        // Assert
        Assertions.assertEquals(newDescription, updatedCategory.getDescription());
    }


    @Test
    void delete() {
    }
}