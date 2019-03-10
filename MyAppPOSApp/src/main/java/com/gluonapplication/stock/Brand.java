package com.gluonapplication.stock;

public enum Brand {

    VALUE('1'){
        public String toString(){
            return "Value";
        }
    },
    PREMIUM('2'){
        public String toString(){
            return "Premium";
        }
    },
    LUXURY('3'){
        public String toString(){
            return "Luxury";
        }
    };

    char productCode;

    Brand (char productCode) {
        this.productCode = productCode;
    }
    
}
