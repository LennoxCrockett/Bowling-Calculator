/* Lennox Crockett 
 * Monday 6:00 - 9:30 
 * Due 10/23/2021
 * */

import java.util.Scanner;

public class BowlingGame {
	// Declaring Variables
		public static Scanner input = new Scanner(System.in);
		public static int[] SCORE = new int[20]; // Holds all of the scores.
		public static int[] BONUS_ROLL = {0, 0}; // Holds bonus scores.
		public static int[] FRAME_SCORE = new int[10]; // Holds total score for each frame.
		public static int[] RUNNING_TOTAL = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		public static int FRAME = 0; // This variable will indicate what frame the user is on.
		public static int ROLL = 1;  // This variable will indicate what attempt/roll the user is on.

	public static void main(String[] args) {
		// Welcomes user.
		System.out.println("WELCOME TO BOWLING!");
		System.out.println("This Program will help you to calculate your score in bowling!");
		System.out.println("Start by entering in your first roll below!\n");
		playGame();       // 1. Gather scores.
		playBonusRolls();  // 2. Give user bonus rolls if necessary.
		calculateBonus(); // 3. Add bonuses to scores if necessary.
		printScore();	  // 4. Print out score card.
		System.out.println("\n\nEND OF PROGRAM");
	} // End of Main method.
	
	public static void playGame() {

		for (int x = 0; x < SCORE.length; x++)
		{	
			if (ROLL == 1) // First roll.
			{	
				// Prompts user to enter score for first attempt/roll.
				System.out.print("Enter your score for roll " + ROLL + " frame " + (FRAME+1) + " (Number Only): ");
				SCORE[x] = input.nextInt();
				
				if (SCORE[x] < 0 || SCORE[x] > 10) // If the user types a score that is not between 0 - 10.
				{
					System.out.println("Score must be between 0 and 10");
					SCORE[x] = 0; // Since user types bad score the score is set to 0 .
					x--;		  // For loop goes back one and therefore the user must re-enter their score.
				}
				else if (SCORE[x] == 10) // Strike
				{
					FRAME_SCORE[FRAME] = SCORE[x]; // Frame score will equal 10 for now.
					SCORE[x+1] = 0; 			   // Since the user scored a strike the next roll will be 0.
					x++; 						   // Skips the scoring for the next one.
					FRAME++; 					   // Moves to the next frame.
				}
				else // No strike
					ROLL = 2;	// Moves to next attempt on current frame.
			}
			else if (ROLL == 2) // Second roll.
			{
				// Prompts user to enter score for second attempt/roll.
				System.out.print("Enter your score for roll " + ROLL + " frame " +  (FRAME+1) + " (Number Only): ");
				SCORE[x] = input.nextInt();
				
				if (SCORE[x] > 10 - SCORE[x-1] || SCORE[x] < 0) /* The first and second score when added 
																together cannot be less than 0 or greater than 10 */
				{
					System.out.println("Score must be between 0 and 10");
					SCORE[x] = 0;
					x--;
				}
				else
				{	
					FRAME_SCORE[FRAME] += (SCORE[x] + SCORE[x-1]); // Calculates current frame score.
					ROLL = 1;							 	   // Goes to the first attempt on the next frame.
					FRAME++;								 	   // Moves to the next frame.
				}

			}
			
		}
	} // End of playGame method.
	
	public static void playBonusRolls() {
		for (int x = 0; x < 1; x++)
		{
			if (SCORE[18] == 10) /* If the user scored a strike in their first roll 
								 for frame 10 they are awarded 2 bonus rolls. */
			{
				System.out.print("Enter your bonus score for roll 2 frame 10 (Number Only): ");
				BONUS_ROLL[0] = input.nextInt();
				if (BONUS_ROLL[0] < 0 || BONUS_ROLL[0] > 10)
				{
					System.out.println("Bonus score must be between 0 and 10");
					BONUS_ROLL[0] = 0;
					x--;
				}
				System.out.print("Enter your bonus score for roll 3 frame 10 (Number Only): ");
				BONUS_ROLL[1] = input.nextInt();
				if (BONUS_ROLL[1] < 0 || BONUS_ROLL[1] > 10)
				{
					System.out.println("Bonus your score must be between 0 and 10");
					BONUS_ROLL[1] = 0;
					x--;
				}
				FRAME_SCORE[9] += BONUS_ROLL[0] + BONUS_ROLL[1]; // Adding bonus rolls to last frame score.
			}
			else if (FRAME_SCORE[9] == 10) // Spare therefore user get awarded 1 bonus roll.
			{
				System.out.print("Enter your bonus score for roll 3 frame 10 (Number Only): ");
				BONUS_ROLL[0] = input.nextInt();
				if (BONUS_ROLL[0] < 0 || BONUS_ROLL[0] > 10)
				{
					System.out.println("Bonus score must be between 0 and 10");
					BONUS_ROLL[0] = 0;
					x--;
				}
				FRAME_SCORE[9] += BONUS_ROLL[0]; // Adding bonus roll to last frame score.
			}
		}
		
	} // End of playBonusGame Method.
	
	public static void calculateBonus() {
		int frame = 0;
		
		for (int x = 0; frame < 9; frame++) // Runs for first 9 frames, 10th frame is calculated in in playBonusGame method.
		{
			if (FRAME_SCORE[frame] == 10 && SCORE[x] != 0 && SCORE[x+1] != 0) // Spare
			{
				FRAME_SCORE[frame] += SCORE[x+2]; // Adds the next roll.
			}
			else if (FRAME_SCORE[frame] == 10 && SCORE[x+2] != 10) // Single Strike
			{
				FRAME_SCORE[frame] += SCORE[x+2] + SCORE[x+3]; // Adds the next 2 rolls.
			}
			else if (FRAME_SCORE[frame] == 10) // Double Strike
			{
				FRAME_SCORE[frame] += 20; // Adds 20
			}
			
			x+=2; // Moves to the score in the next frame.
		}
	} // End of calculateBonus method.
	
	public static void printScore(){
		// Prints out each frame number.
		System.out.printf("\nFrame:%-11s", " ");
		for (int x = 1; x <= 10; x++)
			System.out.printf("%d%-4s", x, " ");
		
		// Prints out results with X,/,- or 1-9.
		System.out.printf("\nResult:%-10s", " ");
		for (int x = 0; x < SCORE.length; x+=2)
		{
			
			
			if (SCORE[x] == 10 && x != 18) 		// If it is not the last score and its a strike than extra spaces will be printed.
				System.out.printf("%-5s", "X");
			else if (SCORE[18] == 10)			// If it is the last score and its a strike than spaces will not be printed.
				System.out.printf("%-2s", "X");	
			
			else if (SCORE[x] + SCORE[x+1] == 10 && x == 18) // If it is the last score than spaces will not be printed.
				System.out.printf("%d/", SCORE[x]);
			else if (SCORE[x] + SCORE[x+1] == 10)			 // If it is not the last score than extra spaces will be printed.
				System.out.printf("%d/%-3s", SCORE[x], " ");  
			
			else if (SCORE[x] == 0 && SCORE[x+1] == 0) // If both scores are 0 just print dashes.
				System.out.printf("- -  ");
			else if (SCORE[x] == 0)
				System.out.printf("- %d  ", SCORE[x+1]); // Which ever score is 0 a dash will be printed.
			else if (SCORE[x+1] == 0)
				System.out.printf("%d -  ", SCORE[x]); 
			else 
				System.out.printf("%d %d  ", SCORE[x], SCORE[x+1]); // Just prints the score since no strike/spare/gutter.
		}
		
		// Prints bonus score to results.
		if (BONUS_ROLL[0] < 10 && BONUS_ROLL[1] == 0 && BONUS_ROLL[0] != 0)
			System.out.print(BONUS_ROLL[0]);
		if (BONUS_ROLL[0] == 10)
			System.out.printf("%-2s", "X");
		if (BONUS_ROLL[0] == 10 && BONUS_ROLL[1] < 10)
			System.out.print(BONUS_ROLL[1]);
		if (BONUS_ROLL[1] == 10)
			System.out.printf("%-2s", "X");
		
		// Prints out each frame score.	
		System.out.printf("\nFrame Score:%-5s", " ");
		for (int x = 0; x < FRAME_SCORE.length; x++)
		{
			if(x == 0)
			{
				RUNNING_TOTAL[x] = FRAME_SCORE[x];
				System.out.printf("%-5d", FRAME_SCORE[x]);
			}
			else
			{
				RUNNING_TOTAL[x] = (FRAME_SCORE[x] + RUNNING_TOTAL[x-1]);
				System.out.printf("%-5d", FRAME_SCORE[x]);
			}
		}
		
		// Prints running total.
		System.out.printf("\nRunning Total:%-3s", " ");
		for (int x = 0; x < RUNNING_TOTAL.length; x++)
		{
			System.out.printf("%-5d", RUNNING_TOTAL[x]);
		}
	} // End of printScore method.
}
