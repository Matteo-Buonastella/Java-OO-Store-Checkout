public class Cart {
    String [] Cart = new String[6];
    int [] Quantity = new int[6];
    double runningTotal = 0;

    //Function takes in the item, quantity, fruit list array from Items class and fruit Price array from Items class
    void buyItem(String item_, int quantity, String fruitList[], double fruitPrice[]){
        int counter = 0; //Counter makes sure Cart and Quantity gets inserted only once
        String item = item_.substring(0, 1).toUpperCase() + item_.substring(1);  //Capitalizes the first letter if not already
        for (int i = 0; i < Cart.length; i++){
            if (Cart[i] != null) {
                if (Cart[i].equals(item) && counter == 0) {    //If item already exists, just increase quantity
                    Quantity[i] += quantity;
                    if (quantity > 1){
                        System.out.println(quantity + " " + item_ + "s Added");
                    }
                    else {
                        System.out.println(quantity + " " + item_ + " Added");
                    }
                    System.out.println("Total number of Apples is now " + Quantity[i] +"\n");
                    counter++;
                }
            }
            else if (Cart[i] != item && Cart[i] == null && counter == 0){    //If item doesn't exist, create item and add quantity
                    int found = 0;
                    for (int j = 0; j < Cart.length; j++){    //Checks through entire cart to see if item exists
                       if (Cart[j] != null){            //If it DOES exist set found to 1
                            if (Cart[j].equals(item)){
                                found++;
                            }
                        }
                    }
                    if (found == 0) {                   //If it couldn't find the item, then add it in a new position in the cart
                        Cart[i] = item;
                        Quantity[i] += quantity;
                        if (quantity > 1) {
                            System.out.println(quantity + " " + item_ + "s Added\n");
                        }
                        else{
                            System.out.println(quantity + " " + item_ + " Added\n");
                        }
                        counter++;
                    }
            }

        }
           setRunningTotal(fruitList, fruitPrice, quantity, item);
           printSumamry();
    }

    //Function sets the running Total
    void setRunningTotal(String fruitList[], double fruitPrice[], int quantity, String item){
        for(int i = 0; i < Cart.length; i++) {  //Adds cost to running total
            if (Cart[i] != null) {
                if (Cart[i].equals(item)) {
                    double price = getCost(item, fruitList, fruitPrice);
                    runningTotal += (price * quantity);
                }
            }
        }
    }

    double getRunningTotal(){
        return runningTotal;
    }

    //Function takes in a fruit, fruit list array and fruit price array and returns the price
    double getCost(String fruit, String fruitList[], double fruitPrice[]){
        for (int i = 0; i < fruitList.length; i++){
            if (fruit.equals(fruitList[i])) {
                return fruitPrice[i];
            }
        }
        return 0;
    }

    //Function removes the entire fruit from cart and sets it to null
    void removeFromCart(String Cart[], String fruit){
        for (int i = 0; i < Cart.length; i++){
            if (Cart[i]!= null){
                if(Cart[i].equals(fruit)){
                    Cart[i] = null;
                }
            }
        }
    }

    //Function recieves a fruit checks to see if it's in the cart
    Boolean fruitInCart(String fruit){
        String exists = fruit.substring(0, 1).toUpperCase() + fruit.substring(1);  //Capitalizes the first letter if not already
        for (int i = 0; i < Cart.length; i++){
            if (Cart[i] != null){
                if (Cart[i].equals(exists)){
                    return true;
                }
            }
        }
        return false;
    }

    //Function prints item, quantity and running Total
    void printSumamry(){
        System.out.println("---Purchase Summary---");
        for (int j = 0; j < Cart.length; j++) {
            if (Cart[j] != null) {
                System.out.println(Cart[j] + " " + Quantity[j]);
            }
        }
        System.out.printf("\nRunning Total: $%.2f", runningTotal);
    }

    //Function receives a fruit and the amount the user wants to remove
    void removeItem(String fruit_, int amount, String fruitList[], double fruitPrice[]){
        String fruit = fruit_.substring(0, 1).toUpperCase() + fruit_.substring(1);  //Capitalizes the first letter if not already
        for (int i = 0; i < Cart.length; i++){
            if (Cart[i] != null){
                if (Cart[i].equals(fruit)){
                    double price = getCost(fruit, fruitList, fruitPrice);  //Gets base price of the fruit
                    if (amount > Quantity[i]){
                        runningTotal -= (Quantity[i] * price);      //Reduce the running total
                    }
                    else {
                        runningTotal -= (amount * price);      //Reduce the running total
                    }
                    Quantity[i] -= amount;
                    if (amount > 1){
                        System.out.println(amount + " " + fruit + "s removed\n");
                    }
                    else{
                        System.out.println(amount + " " + fruit + " removed\n");
                    }
                    if (Quantity[i] <= 0){
                        removeFromCart(Cart, fruit);      //If the Quantity is 0 or less, remove the item from cart
                        Quantity[i] = 0;
                    }
                    if (runningTotal < 0){                //If the running total ever goes below 0, set it to 0
                        runningTotal = 0;
                    }
                   printSumamry();
                }
            }
        }
    }

    //Function receives a fruit and removes it entirely from the cart
    void removeAllItem(String fruit_, String fruitList[], double fruitPrice[]){
        String fruit = fruit_.substring(0, 1).toUpperCase() + fruit_.substring(1);  //Capitalizes the first letter if not already

        for (int i = 0; i < Cart.length; i++){
            if (Cart[i] != null){
                if (Cart[i].equals(fruit)){
                    double price = getCost(fruit, fruitList, fruitPrice);
                    price = price * Quantity[i];
                    runningTotal -= (price);
                    removeFromCart(Cart, fruit);
                    Quantity[i] = 0;
                    printSumamry();
                }
            }
        }
    }

    //Function displays the items of the cart as well as running total
    void displayCart(String fruitList[], double fruitPrice[]){
        System.out.println("---YOUR CART---");
        for (int i = 0; i < Cart.length; i++){
            if(Cart[i] != null) {
                double price = getCost(Cart[i], fruitList, fruitPrice);
                double totalPrice = price*Quantity[i];
                if (Quantity[i] > 1) {
                    System.out.printf(Quantity[i] + " " + Cart[i] + "s $%.2f",  totalPrice);
                    System.out.print("\n");
                }
                else{
                    System.out.printf(Quantity[i] + Cart[i] + " $%.2f",  totalPrice);
                    System.out.print("\n");
                }
            }

        }
        System.out.printf("\nTotal Price: $ %.2f", runningTotal);
    }

}
