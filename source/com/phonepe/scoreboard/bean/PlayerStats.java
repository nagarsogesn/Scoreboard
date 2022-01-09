package com.phonepe.scoreboard.bean;

public class PlayerStats {

	private String name;
	private int score;
	private int fours;
	private int sixes;
	private int balls;
	private boolean notOut;

	public PlayerStats(String playerPlaying) {
		this.name = playerPlaying;
		this.score = 0;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getFours() {
		return fours;
	}

	public void setFours(int fours) {
		this.fours = fours;
	}

	public int getSixes() {
		return sixes;
	}

	public void setSixes(int sixes) {
		this.sixes = sixes;
	}

	public int getBalls() {
		return balls;
	}

	public void setBalls(int balls) {
		this.balls = balls;
	}

	public boolean getNotOut() {
		return notOut;
	}

	public void setNotOut(boolean b) {
		this.notOut = b;
	}
}
