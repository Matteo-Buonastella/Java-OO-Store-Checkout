public class Checkout {
    double HST = 0.13;
    double totalWithTax = 0.0;

    //Main function that forms the receipt
    void Receipt(String Cart[], int [] Quantity, String fruitList[], double fruitPrice[], double runningTotal){
        totalWithTax = totalWithTax(runningTotal);
        receiptHeading();
        itemAndQuantity(Cart,  Quantity,  fruitList, fruitPrice);
        HSTAmount(runningTotal);
        total(runningTotal);
    }

    //Function gets the HST additional price
    void HSTAmount(double runningTotal){
        double HSTPrice = (runningTotal * HST);
        System.out.printf("\t\tHST\t\t  $%.2f", HSTPrice);
        System.out.println("\n\t--------------------");
    }

    //Function prints the heading and date of receipt
    void receiptHeading(){
        System.out.println("\t-----Your Receipt-----");
        java.util.Date date = new java.util.Date();
        System.out.println("\t" + date);
    }

    void itemAndQuantity(String Cart[], int Quantity[], String fruitList[], double fruitPrice[]){
        //String format = "%-40s%s%n";
        System.out.println("\n\t   Item  Qty  Price");
        for (int i = 0; i < Cart.length; i++){
            if(Cart[i] != null) {
                double price = getCost(Cart[i], fruitList, fruitPrice);
                double totalPrice = price*Quantity[i];
                if (Quantity[i] > 1) {
                    System.out.print(String.format("%11s %3s",  Cart[i] , Quantity[i]));
                    System.out.printf("   $%.2f",totalPrice);
                    System.out.println("");
                }
            }
        }
    }

    //Function prints the final amount with HST
    void total(double runningTotal){
        double HSTAmount = getHSTAmount(runningTotal);
        double finalPrice = runningTotal + HSTAmount;
        System.out.printf("\t  Total:\t  $%.2f\n" , finalPrice);
    }

    //Function gets the total price with HST
    double totalWithTax(double runningTotal){
        totalWithTax += runningTotal + (runningTotal*.13);
        return totalWithTax;
    }

    //Function gets the cost of the fruit
    double getCost(String fruit, String fruitList[], double fruitPrice[]){
        for (int i = 0; i < fruitList.length; i++){
            if (fruit.equals(fruitList[i])) {
                return fruitPrice[i];
            }
        }
        return 0;
    }

    //Function returns the HST additional price
    double getHSTAmount(double runningTotal){
        double HSTPrice = (runningTotal * HST);
        return HSTPrice;
    }

}

