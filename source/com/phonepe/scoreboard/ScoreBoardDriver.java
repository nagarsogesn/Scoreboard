package com.phonepe.scoreboard;

import java.util.Scanner;

import com.phonepe.scoreboard.service.MatchService;
import com.phonepe.scoreboard.service.ScoreService;
import com.phonepe.scoreboard.service.impl.MatchServiceImpl;
import com.phonepe.scoreboard.service.impl.PlayerHandler;
import com.phonepe.scoreboard.service.impl.ScoreServiceImpl;

public class ScoreBoardDriver {

	@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		PlayerHandler playerUtil = new PlayerHandler();
		ScoreService scoreTracker = new ScoreServiceImpl(playerUtil);
		MatchService matchService = new MatchServiceImpl(scoreTracker, playerUtil);
		matchService.start();
		choicesAfterMatchEnds(scoreTracker);
	}

	private static void choicesAfterMatchEnds(ScoreService scoreTracker) {
		Scanner scan = new Scanner(System.in);
		while (true) {
			System.out.println("\nMatch is complete. Choose an option: " +
				"\n1. Display Result" +
				"\n2. Display Score(Team 1)" +
				"\n3. Display Score(Team 2)" +
				"\n4. Display Scoreboard(Team 1)" +
				"\n5. Display Scoreboard(Team 2)" +
				"\n6. Display Scoreboard(Both teams)" +
				"\n7. Display Extras" +
				"\n8. Exit");
			int choice = scan.hasNextInt() ? scan.nextInt():7;
			if (choice > 7) {
				break;
			}
			switch (choice) {
			case 1:
				scoreTracker.printResult();
				break;
			case 2:
				scoreTracker.printScore(0);
				break;
			case 3:
				scoreTracker.printScore(1);
				break;
			case 4:
				scoreTracker.printScoreBoard(0);
				break;
			case 5:
				scoreTracker.printScoreBoard(1);
				break;
			case 6:
				scoreTracker.printScoreBoard();
				break;
			case 7:
				scoreTracker.printExtras(0);
				scoreTracker.printExtras(1);
				break;
			default:
				break;
			}
		}

		scan.close();
	}
}
