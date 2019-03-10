package com.gluonapplication.stock;

public enum Type {

    TSHIRT(1000,'1'){
        public String toString(){
            return "T-Shirt";
        }
    },
    SHIRT(1500,'2'){
        public String toString(){
            return "Shirt";
        }
    },
    TROUSER(2000,'3'){
        public String toString(){
            return "Trouser";
        }
    },
    JACKET(3000,'4'){
        public String toString(){
            return "Jacket";
        }
    },
    SWEATER(2000,'5'){
        public String toString(){
            return "Sweater";
        }
    },
    DRESS(4000,'6'){
        public String toString(){
            return "Dress";
        }
    },
    SKIRT (2500,'7'){
        public String toString(){
            return "Skirt";
        }
    },
    SUIT(5000,'8'){
        public String toString(){
            return "Suit";
        }
    },
    ACCESSORY(1000,'9'){
        public String toString(){
            return "Accessory";
        }
    },
    SHOES(2500,'0'){
        public String toString(){
            return "Shoes";
        }
    },
    BOOK(1000,'1'){
        public String toString(){
            return "Book";
        }
    },
    MUSIC(1000,'2'){
        public String toString(){
            return "Music";
        }
    },
    TOY(1000,'3'){
        public String toString(){
            return "Toy";
        }
    },
    KITCKEN(1000,'4'){
        public String toString(){
            return "Kitchen";
        }
    },
    SPORT(2000,'5'){
        public String toString(){
            return "Sport";
        }
    },
    JEWELRY(2500,'6'){
        public String toString(){
            return "Jewelry";
        }
    },
    ANTIQUE_ITEM(5000,'7'){
        public String toString(){
            return "Antique";
        }
    };

    int maxPrice;
    char productCode;

    Type(int maxPrice,char productCode) {
        this.maxPrice = maxPrice;
        this.productCode = productCode;
    }
}
