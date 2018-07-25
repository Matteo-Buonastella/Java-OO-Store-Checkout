import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        Items i = new Items();   //Items object
        Cart c = new Cart();     //Cart object
        Checkout h = new Checkout(); //Checkout object

        int choice = 1;

        System.out.println("--- Welcome to the Store ---");
        while (choice != 0) {
        System.out.println("\nWhat would you like to do\n");
        System.out.println("1. View Items");
        System.out.println("2. Buy Items");
        System.out.println("3. Remove Items");
        System.out.println("4. View Cart");
        System.out.println("5. Checkout");
        System.out.println("0. Exit");

        System.out.print("> ");
        while(!input.hasNextInt()){
            input.next();
            System.out.println("Please enter a valid choice: ");
        }
            choice = input.nextInt();

        switch (choice) {
            case 0:
                System.out.println("Good Bye");
                break;
            case 1:
                i.displayItems();
                break;
            case 2:
                System.out.println("Which fruit would you like to buy");
                String fruit = input.next();
                if (!i.checkItem(fruit)){
                    System.out.println("Sorry, that item doesn't exist.");
                    break;
                }
                System.out.println("How many?");
                while(!input.hasNextInt()){
                    input.next();
                    System.out.println("How many?");
                }
                int quantity = input.nextInt();
                c.buyItem(fruit, quantity, i.fruitList, i.fruitPrice);
                    break;
            case 3:
                System.out.println("Which fruit would you like to remove?: ");
                String removeFruit = input.next();
                if (!i.checkItem(removeFruit)){
                    System.out.println("Sorry, that item doesn't exist.");
                    break;
                }
                if (!c.fruitInCart(removeFruit)){
                    System.out.println("Sorry, that item isn't in your cart");
                    break;
                }
                System.out.println("How many would you like to remove? (type 'all' to remove the entire item): ");
                String removeAll = input.next();
                if (removeAll.equals("all")) {
                    c.removeAllItem(removeFruit, i.fruitList, i.fruitPrice);
                }
                else{
                    int removeAmount = Integer.parseInt(removeAll);
                    c.removeItem(removeFruit, removeAmount, i.fruitList, i.fruitPrice);
                }
                break;
            case 4:
                c.displayCart(i.fruitList, i.fruitPrice);
                break;
            case 5:
                double runningTotal = c.getRunningTotal();
                h.Receipt(c.Cart, c.Quantity, i.fruitList, i.fruitPrice, runningTotal);
                break;
            }
        }
    }
}
