package com.example.major1.Global;

import com.example.major1.Models.Product;

import java.util.ArrayList;
import java.util.List;

public class GlobalData {
    public static List<Product> cart;
    static {
        cart= new ArrayList<>();
    }


}
