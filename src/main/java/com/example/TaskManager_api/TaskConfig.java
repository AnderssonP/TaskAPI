package com.example.TaskManager_api;

import com.example.TaskManager_api.Repository.TaskRepository;
import com.example.TaskManager_api.Task.Task;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TaskConfig {

    @Bean
    CommandLineRunner commandLineRunner(TaskRepository repository){
        return args -> {
            Task tankCar = new Task(
                    "tank Car",
                    "Tank the car to full",
                    "Pending"
            );
            Task parkCar = new Task(
                    "Park car",
                    "Park the car in the garage",
                    "Pending"
            );


//            repository.saveAll(
//                    List.of(tankCar, parkCar )
//            );
        };
    }


}
