package com.phonepe.scoreboard.service.impl;

import static com.phonepe.scoreboard.constants.BallPossibilities.NO_BALL;
import static com.phonepe.scoreboard.constants.BallPossibilities.WICKET;
import static com.phonepe.scoreboard.constants.BallPossibilities.WIDE;

import java.util.Queue;

import com.phonepe.scoreboard.bean.PlayerStats;
import com.phonepe.scoreboard.service.InputService;
import com.phonepe.scoreboard.service.MatchService;
import com.phonepe.scoreboard.service.ScoreService;
import com.phonepe.scoreboard.util.GenericHelpers;

public class MatchServiceImpl implements MatchService {

	private InputService inputService;

	private PlayerHandler playerUtil;

	private ScoreService scoreTracker;

	public MatchServiceImpl(ScoreService scoreTracker, PlayerHandler playerUtil) {
		this.scoreTracker = scoreTracker;
		this.playerUtil = playerUtil;
	}

	@Override
	public void start() {
		inputService = new InputServiceImpl();
		inputService.init();
		Integer noOfPlayers = inputService.noOfPlayers();
		Integer noOfOvers = inputService.noOfOvers();
		completeInning(noOfPlayers, noOfOvers, 0);
		completeInning(noOfPlayers, noOfOvers, 1);
		scoreTracker.printResult();
	}

	private void completeInning(Integer noOfPlayers, Integer noOfOvers, int team) {
		System.out.printf("Batting order for team %d: ", (team + 1));
		Queue<String>  battingOrderTeam1 = inputService.battingOrder(noOfPlayers);
		playerUtil.initializePlayers(battingOrderTeam1, team);
		String player1 = battingOrderTeam1.poll();
		String player2 = battingOrderTeam1.poll();
		playerUtil.setNotOut(team, player1, true);
		playerUtil.setNotOut(team, player2, true);
		String playerOnStrike = player1;
		boolean inningEnded = false;
		PlayerStats curPlayerStats = playerUtil.getStats(team, playerOnStrike);
		for (int over = 1; over <= noOfOvers; over++) {
			System.out.print("Over " + over + ": ");
			int balls = 6;
			while(balls != 0 && !inningEnded) {
				curPlayerStats = playerUtil.getStats(team, playerOnStrike);
				String ballUpdate = inputService.ball();
				switch (ballUpdate) {
					case WICKET:
						balls--;
						curPlayerStats.setBalls(curPlayerStats.getBalls() + 1);
						scoreTracker.addWicket(team);
						playerUtil.setNotOut(team, playerOnStrike, false);
						String nextPlayer = battingOrderTeam1.poll();
						if (nextPlayer == null || (balls == 0 && over == noOfOvers)) {
							inningEnded = true;
							break;
						}
						if (playerOnStrike.equals(player1)) {
							player1 = nextPlayer;
						} else {
							player2 = nextPlayer;
						}
						playerOnStrike = nextPlayer;
						curPlayerStats = playerUtil.getStats(team, playerOnStrike);
						curPlayerStats.setNotOut(true);
						break;

					case WIDE:
						scoreTracker.addWide(team);
						break;

					case NO_BALL:
						scoreTracker.addNoBall(team);
						break;

					default:
						curPlayerStats.setBalls(curPlayerStats.getBalls() + 1);
						int score = Integer.valueOf(ballUpdate);
						playerOnStrike = playerUtil.decideStrike(
							player1,
							player2,
							playerOnStrike,
							score
						);
						playerUtil.setPlayerStats(team, curPlayerStats, score);
						scoreTracker.addScore(score, team);
						balls--;
						break;
				}
				if (balls == 0) {
					playerOnStrike =
						playerUtil.switchStrike(
							player1,
							player2,
							playerOnStrike
						);
				}
			}
			scoreTracker.printScoreBoard(team);
			scoreTracker.printScore(team);
			printOvers(over, balls);
			scoreTracker.printExtras(team);
		}
	}

	private void printOvers(int over, int balls) {
		System.out.println("Overs: " + GenericHelpers.printableOver(balls, over));
	}
}
