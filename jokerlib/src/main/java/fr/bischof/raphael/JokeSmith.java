package fr.bischof.raphael;

import java.util.Random;

public class JokeSmith {

    private static String[] jokes = {
            "Hide and seek champion : \";\" since 1958",
            "Algorithm (noun.) : Word used by programmers when they do not want to explain what they did.",
            "Hardware (noun.) : The part of a computer that you can kick.",
            "Real programmers count from 0.",
            "Chuck Norris writes code that optimizes itself",
            "A SQL query walks into a bar, walks up to two tables and asks : \"Can I join you ?\""
    };

    public static String getARandomJoke(){
        Random randomGenerator = new Random();
        return jokes[randomGenerator.nextInt(5)];
    }
}
