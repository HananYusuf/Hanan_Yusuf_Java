package com.company.factory.IceCream;

import com.company.pointofsale.IceCream.IceCream;

import java.util.ArrayList;
import java.util.List;

public class Icecream {

    private String flavor;
    private int salePrice;
    private int productionCost;
    private int productionTime;
    private List<String> ingredients;

    public boolean isVegan(Icecream icecream){
        icecream.ingredients = new ArrayList<>();
        for(int i = 0; i < icecream.ingredients.size(); i++){
            if(icecream.ingredients.get(i) == "Milk"){
                break;
            }

         }
        return false;
    }

    public boolean isNewFlavor(Icecream icecream){
        return true;
    }


    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getSalePrice() {
        return salePrice;
    }

    public void setSalePrice(int salePrice) {
        this.salePrice = salePrice;
    }

    public int getProductionCost() {
        return productionCost;
    }

    public void setProductionCost(int productionCost) {
        this.productionCost = productionCost;
    }

    public int getProductionTime() {
        return productionTime;
    }

    public void setProductionTime(int productionTime) {
        this.productionTime = productionTime;
    }


    public List<String> getIngredients() {
        return ingredients;
    }

    public void setIngredients(List<String> ingredients) {
        this.ingredients = ingredients;
    }


    @Override
    public String toString() {
        return "Icecream{" +
                "flavor='" + flavor + '\'' +
                ", salePrice=" + salePrice +
                ", productionCost=" + productionCost +
                ", productionTime=" + productionTime +
                ", ingredients=" + ingredients +
                '}';
    }
}
