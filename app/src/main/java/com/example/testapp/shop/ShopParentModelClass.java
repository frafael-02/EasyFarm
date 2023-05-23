package com.example.testapp.shop;

import java.util.ArrayList;
import java.util.List;

public class ShopParentModelClass {
    String title;
    List<ShopChildModelClass> childModelClassList;

    public ShopParentModelClass(String title, List<ShopChildModelClass> childModelClassList) {
        this.title = title;
        this.childModelClassList = childModelClassList;
    }
}
