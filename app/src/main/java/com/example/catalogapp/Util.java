package com.example.catalogapp;

import java.text.DecimalFormat;

public class Util {

    public static Double roundFloatNumber(Float f){
        DecimalFormat df = new DecimalFormat("#.#");
        return Double.parseDouble(df.format(f));
    }
}
