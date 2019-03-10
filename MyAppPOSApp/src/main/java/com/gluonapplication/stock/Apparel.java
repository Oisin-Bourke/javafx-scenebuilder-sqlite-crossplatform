package com.gluonapplication.stock;


public class Apparel extends Item {

    public Apparel(Category category, Type type, Condition condition, Brand brand, Size size) {
        super(category, type, condition);
        this.brand = brand;
        this.size = size;
        System.out.println("apparel constructor called");
    }

    public void generatePrice(Item item){

        price = item.type.maxPrice;

        if (item.condition == Condition.POOR) {
            price = price - (price / 500 * 333);
        }
        if (item.condition == Condition.AVERAGE) {
            price = price - (price / 1000 * 333);
        }

        if (item.brand == Brand.VALUE) {
            price = price - (price / 500 * 333);
        }
        if (item.brand == Brand.PREMIUM) {
            price = price - (price / 1000 * 333);
        }

        price = Math.round((float) price / 50f) * 50;
    }

    public void generateProductCode() {
        productCodeString = Character.toString(category.productCode) + type.productCode + condition.productCode
                + brand.productCode + size.productCode ;
    }

    @Override
    public String toString() {
        return "Apparel: "+ category + "\n"
                +"Type: " + type + "\n"
                +"Condition: " + condition + "\n"
                +"Brand: " + brand + "\n"
                +"Size: " + size + "\n"
                +"Item Code: " + productCodeString + "\t" + "Price: " + formatPrice(price);
    }

}
