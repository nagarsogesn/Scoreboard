package com.phonepe.scoreboard.service;

public interface ScoreService {

	int[] getScore();

	void addWide(int team);

	void addNoBall(int team);

	void addScore(int score, int team);

	int getScore(int team);

	void printScoreBoard(int team);

	void printScoreBoard();

	void printScore(int team);

	void addWicket(int team);

	void printResult();

	void printExtras(int team);
}
