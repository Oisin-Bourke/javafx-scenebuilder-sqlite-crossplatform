package com.gluonapplication.sales;

import com.gluonapplication.stock.Item;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class SalesTransaction {

    private static int counter;

    private final int salesNumber;
    private String dateTime;
    private List <Item> soldItemsList;
    private int salesTotal;


    public SalesTransaction(List <Item> soldItems, int salesTotal) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        this.soldItemsList = soldItems;
        this.dateTime = now.format(formatter);
        this.salesTotal = salesTotal;
        this.salesNumber = counter ++;
        System.out.println("Insider ST constructor content of sold :"+soldItemsList.toString());
    }


    @Override
    public String toString() {
        String a = "Transaction Number: " + salesNumber;
        String b = "Date & Time Sold: " + dateTime;
        String list = soldItemsList.toString();
        //String list = listToString();
        String c = "\t\t\t\t\t\t\t"+"Total €:" + formatPrice(salesTotal);

        return a+"\n"+b+"\n"+list+"\n"+c;
    }

    public int getSalesTotal() {
        return salesTotal;
    }

    public List<Item> getSoldItemsList() {
        return soldItemsList;
    }

    private String listToString(){
        System.out.println("List to String called");
        System.out.println(soldItemsList.isEmpty());

        StringBuilder sb = new StringBuilder();

        for (Item item: soldItemsList){
            System.out.println(item.toString());
            sb.append(item.toString());
        }

        return sb.toString();
    }

    private String formatPrice(int price) {

        DecimalFormat df = new DecimalFormat("#.00");
        return df.format(price / 100.0);
    }
}
