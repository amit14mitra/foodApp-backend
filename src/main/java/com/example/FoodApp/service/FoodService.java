package com.example.FoodApp.service;

import com.example.FoodApp.config.AppConstants;
import com.example.FoodApp.model.Food;
import com.example.FoodApp.repository.FoodRepository;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FoodService {

    private static final Logger logger = LoggerFactory.getLogger(FoodService.class);

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private ProducerService producerService;

    public FoodService(FoodRepository foodRepository) {
        super();
        this.foodRepository = foodRepository;
    }

    @Cacheable("foods")
    public String addFoods(Food food) {
        try {
            foodRepository.save(food);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            return e.getMessage();
        }

//        producerService.updateMessage(food.getFoodName()+" added succesfully");
        logger.info("producer message send........");
        return "successfully added food item ["+food.getFoodNo()+"] in the database..";
    }

    @Cacheable("foods")
    public List<JSONObject> getAllFoods(){
        List<Food> foods=foodRepository.findAll();
        List<JSONObject> foodList=new ArrayList<>();

        JSONObject foodObj=new JSONObject();
        for(Food foo:foods){
//            System.out.println(foo.getJSON());
            foodList.add(foo.getJSON());
        }
//        System.out.println(foodList);
        return foodList;
    }

    @Cacheable("foods")
    public Food getFoodById(String id){
        Optional<Food> food=foodRepository.findById(id);
        return food.orElse(null);
    }

    @Cacheable("foods")
    public List<JSONObject> getFoodByName(String foodName){
        List<Food> foodList= foodRepository.findByFoodNameLike(foodName);
        System.out.println("service foodList--->" + foodList);
        List<JSONObject> fList=new ArrayList<>();
        for(Food fo:foodList){
            fList.add(fo.getJSON());
        }
        return fList;
    }

    @CacheEvict("foods")
    public String deleteFoodById(String id) {
        try {
            foodRepository.deleteById(id);
        } catch (Exception e) {
            return "ERROR while deleting food item ["+id+"]";
        }

        return "successfully deleted food item ["+id+"]";
    }

    @CachePut("foods")
    public String updateFoodById(String id, String food) {

        Food availableFood;

        try {
            JSONObject json=new JSONObject(food);
            availableFood = foodRepository.findById(id)
                    .orElseThrow(() -> new RuntimeException("Food Item is not available"));

            if(json.has("foodNo")) {
                availableFood.setFoodNo(json.getString("foodNo"));
            }
            if(json.has("foodName")) {
                availableFood.setFoodName(json.getString("foodName"));
            }
            if(json.has("foodType")) {
                availableFood.setFoodType(json.getString("foodType"));
            }
            if(json.has("foodPrice")) {
                availableFood.setFoodPrice(json.getDouble("foodPrice"));
            }
            if(json.has("description")) {
                availableFood.setDescription(json.getString("description"));
            }

            foodRepository.save(availableFood);
        }catch(Throwable e) {
            e.printStackTrace();
            return "ERROR while updating food item ["+id+"]";
        }

        return foodRepository.findById(id).get().toString();
    }
}
