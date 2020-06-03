package com.company;

import java.util.HashMap;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        Map<Integer, Integer> m = new HashMap<>();
        m.pu
        // write your code here
        String[] toValidate = {"@string112.cc", "test@one.com"};
        for(String i : toValidate){
            System.out.println(i.matches("^(.+)@([a-z]{0,4}\\.[a-z]+)$"));
        }
    }
}
