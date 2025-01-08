package com.example.FoodApp.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.config.TopicBuilder;

@Configuration
public class KafkaConfig {

//    @Bean
//    public NewTopic createTopic(){
//        return TopicBuilder.name("food_topic").build();
//    }

//    @KafkaListener(topics=AppConstants.FOOD_TOPIC_NAME,groupId = AppConstants.GROUP_ID)
//    public void updateMessage(String message){
//        System.out.println(message);
//    }
}
