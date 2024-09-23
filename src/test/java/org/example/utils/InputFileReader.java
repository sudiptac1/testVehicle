package org.example.utils;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class InputFileReader {
    private static final String regex =  "(?:B ?[0-9]{2,3}|[A-Z]{2} ?[0-9]{2}) ?[A-HJ-NP-Z][A-Z][A-HJ-NP-Z]";
    private static final Pattern pattern = Pattern.compile(regex);


    public static List<String> getCarReg()  {

        List<String> carRegList = new ArrayList<>();
        InputStream inputStream = InputFileReader.class.getClassLoader().getResourceAsStream("input/car_input.txt");
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
               parseFileContent(carRegList,line); ;
            }
        }
        catch (IOException ex){
            System.out.println("Error while reading Input file:"+ex);
        }

        return carRegList;
    }

    private static void  parseFileContent(List<String> carRegList,String lineContent){

        Matcher matcher = pattern.matcher(lineContent);
        while(matcher.find()) {
            carRegList.add(matcher.group());
        }

    }


}
