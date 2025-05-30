package edu.scalable.task4.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import edu.scalable.task4.seed.DatabaseSeeder;

@RestController
public class SeederController {

    private final DatabaseSeeder databaseSeeder;

    @Autowired
    public SeederController(DatabaseSeeder databaseSeeder) {
        this.databaseSeeder = databaseSeeder;
    }

    @GetMapping("/seed")
    public String seedDatabase() {
        databaseSeeder.seedDatabase();
        return "Database seeded successfully!";
    }
}
