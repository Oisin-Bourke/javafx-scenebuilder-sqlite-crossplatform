package com.gluonapplication;

import com.gluonapplication.sales.SalesTransaction;
import com.gluonapplication.stock.*;

import java.util.ArrayList;
import java.util.List;

public class Tester {

    public static void test1(){

        List<Item> stockList = new ArrayList<>();

        System.out.println("Hello world");



        Item test1 = new Item(Category.OTHER, Type.BOOK, Condition.AVERAGE);
        Item test2 = new Item(Category.OTHER,Type.BOOK,Condition.POOR);
        Item test3 = new Item(Category.OTHER,Type.BOOK,Condition.NEW);

        Item apparel1 = new Apparel(Category.MEN,Type.SHIRT,Condition.POOR,Brand.VALUE,Size.MEDIUM);
        Item apparel2 = new Apparel(Category.MEN,Type.SHIRT,Condition.AVERAGE,Brand.VALUE,Size.MEDIUM);
        Item apparel3 = new Apparel(Category.MEN,Type.SHIRT,Condition.AVERAGE,Brand.PREMIUM,Size.MEDIUM);

        stockList.add(test1);
        stockList.add(test2);
        stockList.add(test3);
        stockList.add(apparel1);
        stockList.add(apparel2);
        stockList.add(apparel3);

        for (Item element : stockList){
            element.generatePrice(element);
            element.generateProductCode();
            System.out.println(element.toString());
        }

        test1.setManualPrice(2000,test1);

        System.out.println(test1);

        LinkStringEnum link = new LinkStringEnum();

        Item apparel4 = new Apparel(
                link.linkCategory("Men"),
                link.linkType("Shirt"),
                link.linkCondition("Poor"),
                link.linkBrand("Value"),
                link.linkSize("M")
        );

        Item womenItem = new Apparel(Category.WOMEN,Type.DRESS,Condition.NEW,Brand.LUXURY,Size.MEDIUM);
        womenItem.generatePrice(womenItem);
        womenItem.generateProductCode();

        System.out.println(womenItem.toString());

        System.out.println("This using links");

        apparel4.generatePrice(apparel4);
        apparel4.generateProductCode();
        System.out.println(apparel4.toString());

        Item wonem2 = new Apparel(
                link.linkCategory("Women"),
                link.linkType("Dress"),
                link.linkCondition("Poor"),
                link.linkBrand("Value"),
                link.linkSize("M")
        );

        wonem2.generateProductCode();
        wonem2.generatePrice(wonem2);

        System.out.println(wonem2);


        SalesTransaction salesTransaction = new SalesTransaction(stockList,5000);

        System.out.println(salesTransaction.toString());

        //System.out.println("java version: "+System.getProperty("java.version"));
        //System.out.println("javafx.version: " + System.getProperty("javafx.version"));



    }
}
