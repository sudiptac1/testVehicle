package org.example.utils;



import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;



public class InputFileReader {
    private final String regex =  "(?:B ?[0-9]{2,3}|[A-Z]{2} ?[0-9]{2}) ?[A-HJ-NP-Z][A-Z][A-HJ-NP-Z]";
    private final Pattern pattern = Pattern.compile(regex);



    public List<String> getCarReg() throws IOException {

        List<String> carRegList = new ArrayList<>();
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("input/car_input.txt");
        try (BufferedReader br
                     = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
               parseFileContent(carRegList,line); ;
            }
        }

        return carRegList;
    }

    private void parseFileContent(List<String> carRegList,String lineContent){

        Matcher matcher = pattern.matcher(lineContent);
        while(matcher.find()) {
            carRegList.add(matcher.group());
        }

    }

    //uncomment the below to print the car reg numbers

    /*public static void main(String args[]) throws IOException{
        InputFileReader fr =  new InputFileReader();
        List<String> carRegList = fr.getCarReg();
        carRegList.forEach(System.out::println);
    }*/

}
