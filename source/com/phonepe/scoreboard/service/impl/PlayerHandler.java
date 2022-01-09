package com.phonepe.scoreboard.service.impl;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Queue;

import com.phonepe.scoreboard.bean.PlayerStats;
import com.phonepe.scoreboard.constants.BOUNDARY;

public class PlayerHandler {

	@SuppressWarnings("unchecked")
	Map<String, PlayerStats> playerStats[] =
		new LinkedHashMap[] {
			new LinkedHashMap<String, PlayerStats>(),
			new LinkedHashMap<String, PlayerStats>()
		};

	public void setPlayerStats(int team, PlayerStats curPlayerStats, int score) {
		curPlayerStats.setScore(curPlayerStats.getScore() + score);
		if (score == BOUNDARY.FOUR) {
			curPlayerStats.setFours(curPlayerStats.getFours() + score);
		}
		if (score == BOUNDARY.SIX) {
			curPlayerStats.setSixes(curPlayerStats.getSixes() + score);
		}
	}

	public String decideStrike(String player1, String player2, String playerOnStrike, int score) {
		if (score % 2 == 1) {
			playerOnStrike =
				switchStrike(
					player1,
					player2,
					playerOnStrike
				);
		}
		return playerOnStrike;
	}

	public String switchStrike(String player1, String player2, String playerOnStrike) {
		return playerOnStrike.equals(player1) ? player2 : player1;
	}

	public void initializePlayers(Queue<String> battingOrder, int team) {
		battingOrder.forEach(player ->
			playerStats[team].put(player, new PlayerStats(player)));
	}

	public void setNotOut(int team, String player, boolean notOut) {
		playerStats[team].get(player).setNotOut(notOut);
	}

	public PlayerStats getStats(int team, String player) {
		return playerStats[team].get(player);
	}

	public Map<String, PlayerStats> getStats(int team) {
		return playerStats[team];
	}
}
