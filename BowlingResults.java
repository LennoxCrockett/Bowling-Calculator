/* Lennox Crockett 
 * Monday 6:00 - 9:30 
 * Due 10/23/2021
 * */

import java.util.Scanner;

public class BowlingResults {
	// Declaring Variables
	public static Scanner input = new Scanner(System.in);
	public static int score = 0; // Holds the score for one turn.
	public static int[] scores = new int[21];
	// Holds the total score for a frame.
	public static int[] frameScore = {0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
	public static int frame = 1; // Holds the frame the player is on must be 1 - 9.
	public static int attempt = 1; // Holds the attempt for each frame must be 1 - 3.
	public static int totalScore = 0;
	public static boolean moveOn = false;

	
	public static void main(String[] args) {
		
		System.out.println("WELCOME TO BOWLING!\n");
		while (frame <= 10)
		{
			System.out.print("Enter score for attempt " + attempt + " frame " +  frame + ": ");
			score = input.nextInt();
			scores[3] = input.nextInt();
			
			
			if (score >= 0 && score <= 10 && attempt == 1)
				frameScore[frame] += score;
			else if (score >= 0 && score <= 10 - frameScore[frame] && attempt == 2)
			{
				frameScore[frame] += score;
				moveOn = true;
			}
			else
				moveOn = false;
			
			System.out.println("Current total score: " + frameScore[frame]);
			if(score == 10 && attempt == 1)
			{
				System.out.println("Strike!");
				frameScore[frame] += frameScore[frame - 1];
				attempt = 1;
				frame++;
			}
			else if (frameScore[frame] == 10 && attempt == 2) 
			{
				System.out.println("Spare!");
				attempt = 1;
				frame++;
			}	
			else if (score < 10 && score >= 0 && attempt == 1)
			{
				attempt++;
			}
			else if (score < 10 && score >= 0 && attempt == 2 && moveOn)
			{
				System.out.println("Neither.");
				attempt = 1;
				frame++;
			}	
			else 
			{
				score = 0;
				System.out.println("Score must be between 0 and 10");
	
			}
		}
		
		for (int x = 0; x <= 10; x++)
			totalScore += frameScore[x];
		System.out.println("Your final score is: " + totalScore);
		System.out.println("END OF PROGRAM");
	}

}
