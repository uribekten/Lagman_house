package com.devxschool.food_delivery.repository;

import com.devxschool.food_delivery.models.Food;
import com.devxschool.food_delivery.models.FoodType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class JdbcFoodRepository implements FoodRepository {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public int count() {
        return jdbcTemplate.queryForObject("select count(*) from food", Integer.class);
    }

    @Override
    public int save(Food food) {
        return jdbcTemplate.update(
                "insert into food(food_name, description, price, food_type) values (?,?,?,?)",
                    food.getName(), food.getDescription(), food.getPrice(), food.getFoodType().name()
                );
    }

    @Override
    public int update(Food food) {
        return 0;
    }

    @Override
    public int deleteById(Long id) {
        return jdbcTemplate.update("delete from food where id=?", id);
    }

    @Override
    public List<Food> findAll() {
        return jdbcTemplate.query("select * from food", MAPPER_FOOD);
    }

    @Override
    public Optional<Food> findById(Long id) {
        return jdbcTemplate.query("select * from food where id=?",
                new Object[]{id},
                rs -> rs.next() ? Optional.of(MAPPER_FOOD.mapRow(rs,1)) : Optional.empty()
                );
    }

    @Override
    public List<Food> findByFoodType(String fooType) {
        return null;
    }

    private final RowMapper<Food> MAPPER_FOOD = (rs, rowNum) -> new Food(
            rs.getLong("id"),
            rs.getString("food_name"),
            rs.getFloat("price"),
            rs.getString("description"),
            FoodType.valueOf(rs.getString("food_type"))
    );
}
