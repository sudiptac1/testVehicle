package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.HashMap;


public class OutFileReader {


    public static Map<String, CarRegDetails> getCarDetails() {

        Map<String, CarRegDetails> carRegMap = new HashMap<>();

        InputStream inputStream = OutFileReader.class.getClassLoader().getResourceAsStream("output/car_output.txt");
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            int count = 0;
            while ((line = br.readLine()) != null) {
                ++count;
                if (count > 1) {
                    String[] carInfoArray = line.split(",");
                    carRegMap.put(carInfoArray[0], new CarRegDetails(carInfoArray[0], carInfoArray[1], carInfoArray[2], carInfoArray[3]));
                }
            }
        }
        catch (IOException ex){
            System.out.println("Error while reading Output file:"+ex);
        }

        return carRegMap;
    }


    }







