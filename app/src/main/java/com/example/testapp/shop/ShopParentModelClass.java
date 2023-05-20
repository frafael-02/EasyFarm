package com.example.testapp.shop;

import java.util.ArrayList;

public class ShopParentModelClass {
    String title;
    ArrayList<ShopChildModelClass> childModelClassList;

    public ShopParentModelClass(String title, ArrayList<ShopChildModelClass> childModelClassList) {
        this.title = title;
        this.childModelClassList = childModelClassList;
    }
}
