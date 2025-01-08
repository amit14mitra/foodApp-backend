package com.example.FoodApp.service;

import com.example.FoodApp.config.AppConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
public class ProducerService {

    @Autowired
    public KafkaTemplate<String, String> kafkaTemplate;

    public boolean updateMessage(String message){
        kafkaTemplate.send(AppConstants.FOOD_TOPIC_NAME, message);
        return true;
    }
}
