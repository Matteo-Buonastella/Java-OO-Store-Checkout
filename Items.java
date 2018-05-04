public class Items {
        String [] fruitList = {"Apple", "Banana", "Dragonfruit", "Grape", "Mango", "Pear", "Pineapple"};
        double [] fruitPrice = {0.75, 1.0, 2.0, 0.2, 1.3, 1.2, 1.5};

        //Function displays the list of fruit followed by its price
        void displayItems(){
            System.out.println("---Inventory---");
            for (int i = 0; i < fruitList.length; i++){
                System.out.println("[" + i + "] " + fruitList[i] + " $" + fruitPrice[i]);
            }
        }

        //Function recieves a fruit anc checks if it exists
        Boolean checkItem(String fruit){
            String cap = fruit.substring(0, 1).toUpperCase() + fruit.substring(1);  //Capitalizes the first letter if not already
            boolean found = false;
            for(int i = 0; i < fruitList.length; i++){
                if (fruitList[i].equals(cap)){
                    found = true;
                }
            }
            return found;
        }

}
