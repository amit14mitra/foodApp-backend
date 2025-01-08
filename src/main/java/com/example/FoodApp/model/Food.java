package com.example.FoodApp.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "food_item")
@Data
public class Food {

    @Id
    private String foodNo;
    private String foodName;
    private String foodType;
    private double foodPrice;
    private String description;

    public Food(){

    }

    public Food(String message){

    }

    public Food(String foodNo, String foodName, String foodType, double foodPrice, String description) {
        this.foodNo = foodNo;
        this.foodName = foodName;
        this.foodType = foodType;
        this.foodPrice = foodPrice;
        this.description = description;
    }

    public String getFoodType() {
        return foodType;
    }

    public void setFoodType(String foodType) {
        this.foodType = foodType;
    }

    public String getFoodNo() {
        return foodNo;
    }

    public void setFoodNo(String foodNo) {
        this.foodNo = foodNo;
    }

    public String getFoodName() {
        return foodName;
    }

    public void setFoodName(String foodName) {
        this.foodName = foodName;
    }

    public double getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(double foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Food{" +
                "foodNo=" + foodNo +
                ", foodName='" + foodName + '\'' +
                ", foodType='" + foodType + '\'' +
                ", foodPrice=" + foodPrice +
                ", description='" + description + '\'' +
                '}';
    }

    public JSONObject getJSON() {
        JSONObject json=new JSONObject();

        json.put("foodNo", this.foodNo);
        json.put("foodName", this.foodName);
        json.put("foodType", this.foodType);
        json.put("foodPrice", this.foodPrice);
        json.put("description", this.description);

        return json;
    }
}
