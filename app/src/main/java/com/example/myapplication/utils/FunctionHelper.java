package com.example.myapplication.utils;

import java.text.DecimalFormat;

public class FunctionHelper {

    // Method to format the price in rupiah format
    public static String rupiahFormat(int price) {
        DecimalFormat formatter = new DecimalFormat("#,###");
        return "Rp " + formatter.format((long) price).replace(",", ".");
    }
}