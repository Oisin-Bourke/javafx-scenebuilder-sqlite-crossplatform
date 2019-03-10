package com.gluonapplication.stock;

public enum Size {

    X_SMALL('1',"XS"){
        public String toString(){
            return "XS";
        }
    },
    SMALL('2',"S"){
        public String toString(){
            return "S";
        }
    },
    MEDIUM('3',"M"){
        public String toString(){
            return "M";
        }
    },
    LARGE('4',"L"){
        public String toString(){
            return "L";
        }
    },
    X_LAGER('5',"XL"){
        public String toString(){
            return "XL";
        }
    };

    char productCode;
    String sizeChars;

    Size(char productCode,String sizeChars) {
        this.productCode = productCode;
        this.sizeChars = sizeChars;
    }

}
