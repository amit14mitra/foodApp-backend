package com.example.FoodApp.controller;

import com.example.FoodApp.exception.FoodNotFoundException;
import com.example.FoodApp.model.Food;
import com.example.FoodApp.model.FoodRequest;
import com.example.FoodApp.repository.FoodRepository;
import com.example.FoodApp.service.FoodService;
import com.example.FoodApp.service.StripeService;
import com.example.FoodApp.util.StripeResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import netscape.javascript.JSObject;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/foods")
public class FoodController {

    Logger logger= LoggerFactory.getLogger(this.getClass());

    @Autowired
    private FoodService foodService;

    @Autowired
    private StripeService stripeService;

    @Autowired
    private FoodRepository foodRepository;

    @Autowired
    private ObjectMapper objectMapper;

    public FoodController(FoodService foodService) {
        this.foodService = foodService;
    }

    @GetMapping("/welcome")
    public String welcomeMessage() {
        return "welcome to Food service application";
    }

    @PostMapping("/addFoods")
    public String addFoods(@RequestBody Food food) {
        logger.info("Inside Add Food service");

        logger.debug("Food Item :: " + food);
        return foodService.addFoods(food);
    }

    @GetMapping("/getFood/{param}")
    public ResponseEntity<?> getFoodById(@PathVariable String param){
        List<JSONObject> fo = foodService.getAllFoods();
        Optional<JSONObject> food=fo.stream().filter(c->
                c.getString("foodNo").equalsIgnoreCase(param)).findFirst();

        if(food.isPresent()) {
            fo.clear();
            fo.add(food.get());
            return ResponseEntity.ok(fo.toString());
        } else {
//            System.out.println("fooooo--->>>"+fo);
            List<JSONObject> foodList = foodService.getFoodByName(param);
//            System.out.println("foodListByName---->"+foodList);
            if(!foodList.isEmpty()){
                return ResponseEntity.ok(foodList.toString());
            } else {
                throw new FoodNotFoundException("Food item not found for " + param);
            }
        }
    }

    @GetMapping("/getFoodById/{id}")
    public Food getFoodByParticularId(@PathVariable String id){
        if (foodRepository.findById(id).isEmpty())
            throw new FoodNotFoundException("Food item not found with this id value : " + id);

        return foodRepository.findById(id).get();
    }

    @GetMapping("/getFoods")
    public ResponseEntity<?> getFoods() {
        logger.info("Inside Get Food Service");
        List<JSONObject> foods=foodService.getAllFoods();

        return ResponseEntity.ok(foods.toString());
    }

    @PutMapping("/updateFood/{id}")
    public String updateFoods(@PathVariable String id,@RequestBody String food) {
        return foodService.updateFoodById(id,food);
    }
    
    @DeleteMapping("/deleteFood/{id}")
    public String deleteFoods(@PathVariable String id) {
        return foodService.deleteFoodById(id);
    }

    @PostMapping("/checkout")
    public ResponseEntity<StripeResponse> checkoutProducts(@RequestBody FoodRequest foodRequest) {
        System.out.println("Inside stripe controller....");
        StripeResponse stripeResponse = stripeService.checkoutFoods(foodRequest);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stripeResponse);
    }

//    @GetMapping("/success")
//    public String getSuccessResponse() {
//        return "Success";
//    }
//
//    @GetMapping("/cancel")
//    public String getCancellationResponse() {
//        return "Cancel";
//    }
}
