package com.example.weather;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
class WeatherApplicationTests {

    @Test
    void equalTest(){
        assertEquals(1,1);
    }

    @Test
    void nullTest(){
        assertNotNull(1);
    }

}
