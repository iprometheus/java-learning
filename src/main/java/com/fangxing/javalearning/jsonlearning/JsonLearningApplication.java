package com.fangxing.javalearning.jsonlearning;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.xml.internal.bind.v2.TODO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


public class JsonLearningApplication {

    public static void main(String[] args) throws Exception {

        String jsonStr = "{\"name\":\"suxiaolong\",\"sex\":\"男\",\"age\":30}";

        ObjectMapper objectMapper = new ObjectMapper();

        //Json转对象

        Student student = objectMapper.readValue(jsonStr, Student.class);
        System.out.println(student);

        //String转Json
        JsonNode jsonNode = objectMapper.readTree(jsonStr);
        System.out.println(jsonNode);
        System.out.println(jsonNode.get("name"));

        //对象转Json的String
        System.out.println(objectMapper.writeValueAsString(student));

    }

}
