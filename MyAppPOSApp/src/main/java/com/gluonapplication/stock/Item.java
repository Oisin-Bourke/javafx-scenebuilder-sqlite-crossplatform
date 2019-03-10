package com.gluonapplication.stock;
import java.text.DecimalFormat;

public class Item {

    protected Category category;
    protected Type type;
    protected Condition condition;
    protected Brand brand;
    protected Size size;

    protected int price;
    protected String productCodeString;

    public Item(){
        System.out.println("default constructor called");
    }

    public Item(Category category, Type type, Condition condition) {
        this.category = category;
        this.type = type;
        this.condition = condition;

        System.out.println("item constructor called");
    }

    public void generatePrice (Item item){

        price = item.type.maxPrice;

        if (item.condition == Condition.AVERAGE) {
            price = price - (price / 1000 * 333);
        }
        if (item.condition == Condition.POOR) {
            price = price - (price / 500 * 333);
        }

        price = Math.round((float) price / 50f) * 50;
    }

    public void setManualPrice(int price,Item item) {
        if(price < item.type.maxPrice) {
            this.price = price;
        } else {
            System.out.println(price + " exceeds maximum price");
        }
    }

    public void generateProductCode() {
        productCodeString = Character.toString(category.productCode) + type.productCode + condition.productCode;
    }

    public Category getCategory() {
        return category;
    }

    public Type getType() {
        return type;
    }

    public Condition getCondition() {
        return condition;
    }

    public Brand getBrand() {
        return brand;
    }

    public Size getSize() {
        return size;
    }

    public int getPrice(){
        return price;
    }

    public String getProductCodeString() {
        return productCodeString;
    }

    @Override
    public String toString() {

        return "Item: " + category + "\n"
                +"Type: " + type + "\n"
                +"Condition: " + condition + "\n"
                +"Item Code: " + productCodeString + "\t" + "Price: " + formatPrice(price);
    }

    public String formatPrice(int price) {
        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(price / 100.0);
    }

}
