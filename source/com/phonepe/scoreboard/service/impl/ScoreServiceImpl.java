package com.phonepe.scoreboard.service.impl;

import java.util.Map;

import com.phonepe.scoreboard.bean.PlayerStats;
import com.phonepe.scoreboard.constants.ScoreCardTitleWidth;
import com.phonepe.scoreboard.constants.ScoreCardTitles;
import com.phonepe.scoreboard.service.ScoreService;
import com.phonepe.scoreboard.util.GenericHelpers;

public class ScoreServiceImpl implements ScoreService {

	private PlayerHandler playerUtil;

	int score[] = new int[] {0, 0};

	int wides[] = new int[] {0, 0};

	int noBalls[] = new int[] {0, 0};

	int wickets[] = new int[] {0, 0};

	public ScoreServiceImpl(PlayerHandler playerUtil) {
		this.playerUtil = playerUtil;
	}

	@Override
	public int[] getScore() {
		return score;
	}

	@Override
	public void addWide(int team) {
		score[team]++;
		wides[team]++;
	}

	@Override
	public void addNoBall(int team) {
		score[team]++;
		noBalls[team]++;
	}

	@Override
	public void addScore(int score, int team) {
		this.score[team] += score;
	}

	@Override
	public int getScore(int team) {
		return score[team];
	}

	@Override
	public void printScoreBoard(int team) {
		System.out.println("Scoreboard for Team: " + (team + 1));

		String format = getPrintFormat();

		//Print title
		GenericHelpers.printHorizontalLine(ScoreCardTitleWidth.TOTAL_WIDTH);
		System.out.println("\n" + String.format(
			format,
			ScoreCardTitles.PLAYER_NAME,
			ScoreCardTitles.SCORE,
			ScoreCardTitles.FOURS,
			ScoreCardTitles.SIXES,
			ScoreCardTitles.BALLS,
			ScoreCardTitles.STRIKE_RATE
		));
		GenericHelpers.printHorizontalLine(ScoreCardTitleWidth.TOTAL_WIDTH);
		System.out.println();

		Map<String, PlayerStats> playerStats = playerUtil.getStats(team);
		//Print player stats
		for (String player : playerStats .keySet()) {
			PlayerStats stats = playerStats.get(player);
			System.out.println(
				String.format(
					format,
					player + (stats.getNotOut() ? "*" : ""),
					stats.getScore(),
					stats.getFours(),
					stats.getSixes(),
					stats.getBalls(),
					GenericHelpers.strikeRate(stats.getBalls(), stats.getScore())
				)
			);
		}
		GenericHelpers.printHorizontalLine(ScoreCardTitleWidth.TOTAL_WIDTH);

	}

	private String getPrintFormat() {
		String formatPlayerName =
			GenericHelpers.formatWidth(ScoreCardTitleWidth.PLAYER_NAME, 1);
		String formatScore =
			GenericHelpers.formatWidth(ScoreCardTitleWidth.SCORE, 2);
		String formatFours =
			GenericHelpers.formatWidth(ScoreCardTitleWidth.FOURS, 3);
		String formatSixes =
			GenericHelpers.formatWidth(ScoreCardTitleWidth.SIXES, 4);
		String formatBalls =
			GenericHelpers.formatWidth(ScoreCardTitleWidth.BALLS, 5);
		String formatSR =
			GenericHelpers.formatWidth(ScoreCardTitleWidth.STRIKE_RATE, 6);
		String format = formatPlayerName + formatScore + formatFours +
			formatSixes + formatBalls + formatSR;
		return format;
	}

	@Override
	public void printScoreBoard() {
		printScoreBoard(0);
		printScoreBoard(1);
	}

	public void printScore(int team) {
		System.out.println("\nTotal: " + score[team] + "/" + wickets[team]);
	}

	@Override
	public void addWicket(int team) {
		wickets[team]++;
	}

	@Override
	public void printResult() {
		int diff = Math.abs(score[0] - score[1]);
		if (score[0] > score[1]) {
			System.out.printf("Team 1 won by %d runs!\n", diff);
		} else if (score[0] < score[1]) {
			System.out.printf("Team 2 won by %d runs!\n", diff);
		} else {
			System.out.printf("Match is Draw!");
		}
	}

	@Override
	public void printExtras(int team) {
		System.out.println("Team "+(team + 1)+" Extras: " + (wides[team] + noBalls[team]));
	}
}
