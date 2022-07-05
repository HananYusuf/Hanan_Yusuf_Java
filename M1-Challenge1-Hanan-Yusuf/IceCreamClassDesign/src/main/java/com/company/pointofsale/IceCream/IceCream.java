package com.company.pointofsale.IceCream;

import com.company.factory.IceCream.Icecream;

import java.util.List;

public class IceCream {

    private String flavor;
    private int price;
    private int quantity;

    private int numberOfToppings;

    private int soldAmount;

    public void monthSpecial(List<IceCream> iceCreamList){
        int special = 0;

           for(int i = 0; i < iceCreamList.size(); i++) {
                if (iceCreamList.get(i).getSoldAmount() > special){
                    special = iceCreamList.get(i).getSoldAmount();
                    System.out.println(iceCreamList.get(i).getFlavor() + "This month's special");
                }
           }

        }


    public int calculateCost(){
        int total = 0;
        total = (price * quantity) + numberOfToppings;
        return total;
    }

    public void addTopping(){

        this.numberOfToppings++;
    }

    public boolean isNewFlavour(Icecream icecream){
        return true;
    }

    public String getFlavor() {
        return flavor;
    }

    public void setFlavor(String flavor) {
        this.flavor = flavor;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void increaseQuantity(){
        this.quantity++;
    }

    public void setNumberOfToppings(int numberOfToppings) {
        this.numberOfToppings = numberOfToppings;
    }

    public int getSoldAmount() {
        return soldAmount;
    }

    public void setSoldAmount(int soldAmount) {
        this.soldAmount = soldAmount;
    }

    @Override
    public String toString() {
        return "IceCream{" +
                "flavor='" + flavor + '\'' +
                ", price=" + price +
                ", quantity=" + quantity +
                ", numberOfToppings=" + numberOfToppings +
                ", soldAmount=" + soldAmount +
                '}';
    }
}
