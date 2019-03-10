package com.gluonapplication.stock;

public enum Category {

    MEN('M'){
        public String toString(){
            return "Men";
        }
    },
    WOMEN('W'){
        public String toString(){
            return "Women";
        }
    },
    KID('K'){
        public String toString(){
            return "Kid";
        }
    },
    OTHER('O'){
        public String toString(){
            return "Other";
        }
    };

    char productCode;

    Category (char productCode) {
        this.productCode = productCode;
    }

}
