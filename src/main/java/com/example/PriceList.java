package com.example;

import java.util.List;

public class PriceList {
 public static float getInspectionPrice() {
    return 550f;
 }
 //
 public static float getServicePrice(int productionYear) {
    if (productionYear>2020) return 1500f;
    else if (productionYear >= 2015 ) return 1800f;
    else if (productionYear >= 2010 ) return 2000f;
    else if (productionYear >= 2005 ) return 2300f;
    else return 2800f;
 }

 public static float getRepairPrice() {
    // Reparations is a flexible price. Manual input
     return 0f;
 }

}
