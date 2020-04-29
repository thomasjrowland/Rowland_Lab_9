package Lab9_Rowland;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.InputMismatchException;
import java.util.Map;
import java.util.Scanner;

public class RowlandLab9 {
	
	private static Scanner scnr = new Scanner(System.in);

	public static void main(String[] args) {

		HashMap<String, Double> storeInventory = new HashMap<>();
		HashMap<String, Double> userList = new HashMap<>();
		HashMap<String, Integer> userQuan = new HashMap<>();
		ArrayList<String> foodItem = new ArrayList<>();
		ArrayList<Double> itemPrice = new ArrayList<>();
		
		storeInventory.put("Eggs", 2.49);
		storeInventory.put("Bacon", 6.99);
		storeInventory.put("Bread", 3.49);
		storeInventory.put("Avocado", 1.49);
		storeInventory.put("Onion", 0.69);
		storeInventory.put("Jalapeno", 0.29);
		storeInventory.put("Mayo", 3.79);
		storeInventory.put("Asparagus", 2.19);
		storeInventory.put("Caviar", 145.29);
				
		System.out.println("WELCOME TO THE FOOD STORE!");
		System.out.println();
		
		do {			
		
			int menuNum = 1;
			System.out.println("Here's what we have in stock:");
			System.out.println();
			for (Map.Entry<String, Double> entry : storeInventory.entrySet()) {
				System.out.printf("(%2d )", menuNum);
				System.out.printf("%11s  :  $%7.2f\n",entry.getKey(), entry.getValue());
				foodItem.add(entry.getKey());
				itemPrice.add(entry.getValue());
				menuNum++;
			}
		
			System.out.println();
			int itemSelection = getInt(scnr, "Please make your selection: ", 1, foodItem.size());
			int quantity = getInt(scnr, "How many " + foodItem.get(itemSelection - 1) + "s would you like? ", 1, 1000);
			
			System.out.println("Adding " + foodItem.get(itemSelection - 1) + " for $" + itemPrice.get(itemSelection - 1) + " each.");
			
			userList.put(foodItem.get(itemSelection - 1), itemPrice.get(itemSelection - 1));
			userQuan.put(foodItem.get(itemSelection-1), quantity);
					
		} while (getYesNo(scnr, "Would you like to add another item? "));
		
		System.out.println();
		System.out.println();
		System.out.println("You Bought the following:");
		System.out.println("=================================");
		for (Map.Entry<String, Double> entry : userList.entrySet()) {
			System.out.printf("%3d - ",userQuan.get(entry.getKey()));
			System.out.printf("%-11s  :  $%7.2f\n",entry.getKey(), (entry.getValue()) * (userQuan.get(entry.getKey())));
			userList.replace(entry.getKey(), (entry.getValue()) * (userQuan.get(entry.getKey())));
		}
		System.out.println("=================================");
		System.out.printf("Your item average is: $%.2f\n", groceryAvg(userList));
		
		
		

	}
	
	public static int getInt(Scanner scnr, String prompt, int min, int max) {
		System.out.print(prompt);
		
			try {
				int num = scnr.nextInt();
				scnr.nextLine();
				if (num >= min && num <= max) {
					return num;
				} else {
					return getInt(scnr, prompt, min, max);
				}
			} catch (InputMismatchException e) {
				System.out.println("Enter a whole number, using digits.");
				scnr.nextLine();
				return getInt(scnr, prompt, min, max);
			}
	}

	public static boolean getYesNo(Scanner scnr, String prompt) {
		
		System.out.print(prompt);
		String input = scnr.nextLine().toLowerCase();
		boolean isValid = input.equals("y") || input.equals("n");
		
		while (!isValid) {
			System.out.println("Please type Y or N");
			input = scnr.nextLine().toLowerCase();
			isValid = input.equals("y") || input.equals("n");
		}
		return input.startsWith("y");

}
	
	public static double groceryAvg(HashMap<String, Double> userList) {
		double avg = 0.0;
		
		for (Map.Entry<String, Double> entry : userList.entrySet()) {
			avg += entry.getValue();
		}
		
		avg = avg / userList.size();
		
		return avg;
	}
	
}
