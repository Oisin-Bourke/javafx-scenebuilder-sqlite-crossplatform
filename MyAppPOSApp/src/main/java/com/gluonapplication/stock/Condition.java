package com.gluonapplication.stock;

public enum Condition {

    POOR('1'){
        public String toString(){
            return "Poor";
        }
    },
    AVERAGE('2'){
        public String toString(){
            return "Average";
        }
    },
    NEW('3'){
        public String toString(){
            return "New";
        }
    };

    char productCode;

    Condition(char productCode) {
        this.productCode = productCode;
    }

}
