package com.gluonapplication.stock;

public class LinkStringEnum {

    public Category linkCategory(String string) {

        switch (string) {
            case "Men":
                return Category.MEN;
            case "Women":
                return Category.WOMEN;
            case "Kid":
                return Category.KID;
            case "Other":
                return Category.OTHER;
            default:
                break;
        }
        return null;
    }

    public Type linkType(String string) {

        switch (string) {
            case "T-Shirt":
                return Type.TSHIRT;
            case "Shirt":
                return Type.SHIRT;
            case "Sweater":
                return Type.SWEATER;
            case "Suit":
                return Type.SUIT;
            case "Trouser":
                return Type.TROUSER;
            case "Jacket":
                return Type.JACKET;
            case "Dress":
                return Type.DRESS;
            case "Skirt":
                return Type.SHIRT;
            case "Accessory":
                return Type.ACCESSORY;
            case "Shoes":
                return Type.SHOES;
            case "Book":
                return Type.BOOK;
            case "Music":
                return Type.MUSIC;
            case "Toy":
                return Type.TOY;
            case "Kitchen":
                return Type.KITCKEN;
            case "Sport":
                return Type.SPORT;
            case "Jewelry":
                return Type.JEWELRY;
            case "Antique":
                return Type.ANTIQUE_ITEM;
            default:
                break;
        }
        return null;
    }

    public Size linkSize(String string) {

        switch(string) {
            case "XS":
                return Size.X_SMALL;
            case "S":
                return Size.SMALL;
            case "M":
                return Size.MEDIUM;
            case "L":
                return Size.LARGE;
            case "XL":
                return Size.X_LAGER;
            default:
                break;
        }
        return null;
    }

    public Brand linkBrand(String string){

        switch (string) {
            case "Value":
                return Brand.VALUE;
            case "Premium":
                return Brand.PREMIUM;
            case "Luxury":
                return Brand.LUXURY;
            default:
                break;
        }
        return null;
    }

    public Condition linkCondition(String string){

        switch (string) {
            case "Poor":
                return Condition.POOR;
            case "Average":
                return Condition.AVERAGE;
            case "New":
                return Condition.NEW;
            default:
                break;
        }
        return null;
    }

}