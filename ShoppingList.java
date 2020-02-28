package lab9;
//Author:Jackie Zablah. Java Bootcamp 2019
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

public class ShoppingList {


	public static void main(String[] args) {

		Scanner scnr = new Scanner(System.in); // object to store user input
		String userContinue = "y"; // store user choice to continue the program
		int userOrder = 0; //store from the list the num of the item ordered
		int quantity = 0; // store quantity of each item the user ordered
		

		Map<Integer, List<String>> menuItems = new HashMap<>(); // Map with number for the list of menu Items
		menuItems.put(1, new ArrayList<String>());
		menuItems.get(1).add("bananas");
		menuItems.get(1).add("0.43");
		menuItems.put(2, new ArrayList<String>());
		menuItems.get(2).add("apples");
		menuItems.get(2).add("0.99");
		menuItems.put(3, new ArrayList<String>());
		menuItems.get(3).add("berries");
		menuItems.get(3).add("1.25");
		menuItems.put(4, new ArrayList<String>());
		menuItems.get(4).add("cucumber");
		menuItems.get(4).add("0.50");
		menuItems.put(5, new ArrayList<String>());
		menuItems.get(5).add("zucchini");
		menuItems.get(5).add("0.35");
		menuItems.put(6, new ArrayList<String>());
		menuItems.get(6).add("jalapenio");
		menuItems.get(6).add("1.35");
		menuItems.put(7, new ArrayList<String>());
		menuItems.get(7).add("garlic");
		menuItems.get(7).add("1.60");
		menuItems.put(8, new ArrayList<String>());
		menuItems.get(8).add("onion");
		menuItems.get(8).add("1.15");

		ArrayList<String> userItems = new ArrayList<>(); // arrayList to record User ordered items 
		ArrayList<Double> userPrice = new ArrayList<>(); // arrayList to store the prices of the ordered items	
		ArrayList<Double> userSubTotal = new ArrayList<>(); // arrayList to store the subtotal of each item ordered


		System.out.println("Welcome to Super Fresh's Market!\n");
		//  displaying menu of items and prices
		do {
			System.out.printf("%-18s %-10s\n", "   Item", "Price");
			System.out.println("==============================");

			for(Entry<Integer, List<String>> items: menuItems.entrySet()) {
				System.out.print(items.getKey() +  ". ");
				System.out.printf("%-14s",items.getValue().get(0));
				System.out.printf("%-1s", " ");
				System.out.printf("%-1s %-15s\n" , "$" , items.getValue().get(1));
			}//end for
			
			
			System.out.print("What item would you like to order? (enter a number: 1-8): ");
			
			//validate user input if not integers or not in the range 1-8
			if(scnr.hasNextInt()) {
				userOrder = scnr.nextInt();
				if((userOrder<=8 && userOrder>=1)) {
					
					System.out.print("How many " + menuItems.get(userOrder).get(0) + " would you like to order? "); 
					if(scnr.hasNextInt()) { //validation if input has integer
						quantity = scnr.nextInt(); 
						if(!(quantity<=0)) { //validation greater than 0
						
							System.out.println("\nAdding " + quantity + " " + menuItems.get(userOrder).get(0) + " to cart at $" + 
									menuItems.get(userOrder).get(1) + " each" );
							userItems.add(menuItems.get(userOrder).get(0)); 
							String price = 	menuItems.get(userOrder).get(1); //getting price
							userPrice.add(Double.parseDouble(price)); //converting to double
							userSubTotal.add(Double.parseDouble(price) * quantity); 
							System.out.println();
							System.out.println("Would you like to continue? (y/n) "); 
							userContinue = scnr.next();
						}//quantity greater than 0
						else {
							System.out.println("Cancelled order.");
							scnr.nextLine();
						}
					}//scanner has next int for quantity
					else {
						System.out.println("Cancelled order.");
						scnr.nextLine();
						scnr.nextLine();
					}
				}//user order between range
				else {
					System.out.println("Enter Only a number 1- 8. Please try again.\n");
					scnr.nextLine();
				}//end else
			}//scanner has next int for user order		
			else {
				System.out.println("Enter Only a number 1- 8. Please try again.\n");
				scnr.nextLine();
				continue;
			}//end else
		} //end do

	
		while (userContinue.equalsIgnoreCase("y"));
		System.out.println();
		System.out.println("Thanks for shopping with us!");
		System.out.println();
		System.out.println("Here is your order: ");

		System.out.printf("%-12s %-8s %-8s\n", "Item", "Price", "   subTotal");
		System.out.printf("%-10s \n","===================================");

		for (int i = 0; i < userItems.size(); i++) {
			System.out.printf("%-11s %-1s %.2f %-1s %.2f\n", userItems.get(i)," $",userPrice.get(i), "    $", userSubTotal.get(i));
		}//end for
		System.out.println();
		System.out.printf("Average price per item in order was $%.2f ", findAveragePrice(userPrice, userItems.size()));
		System.out.println("\nLowest price index in the order was " + findLowestPriceIndex(userPrice, userItems.size()) + 
				" which was the " + userItems.get(findLowestPriceIndex(userPrice, userItems.size())));
		System.out.println("Highest price index in the order was " + findHighestPriceIndex(userPrice, userItems.size()) + 
				" which was the " + userItems.get(findHighestPriceIndex(userPrice, userItems.size())));
		scnr.close();

	}//end main



	private static double findAveragePrice(ArrayList<Double> userPrice, int arraySize) {
		double average = 0.0;
		double pricesSum = 0.0;
		for (int i = 0; i < arraySize; i++) {
			pricesSum+=userPrice.get(i);
		}//end for
		average = pricesSum/(arraySize);
		return average;
	}//end findAveragePrice

	private static int findLowestPriceIndex(ArrayList<Double> userPrice, int arraySize) {
		double minPrice = userPrice.get(0);
		int index = 0;
		for (int i = 0; i < arraySize; i++) {

			if(minPrice > userPrice.get(i)) {
				minPrice = userPrice.get(i);
				index = i;
			}
		}//end for
		return index;
	}//end findLowestPriceIndex


	private static int findHighestPriceIndex(ArrayList<Double> userPrice, int arraySize) {
		double maxPrice = userPrice.get(0);
		int index = 0;
		for (int i = 0; i < arraySize; i++) {
			if(maxPrice < userPrice.get(i)) {
				maxPrice = userPrice.get(i);
				index = i;
			}
		}//end for

		return index;	
	}// end findHighestPriceIndex


}//end ShoppingList class
