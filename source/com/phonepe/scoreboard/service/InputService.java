package com.phonepe.scoreboard.service;

import java.util.Queue;

public interface InputService {

	Integer noOfPlayers();

	Integer noOfOvers();

	String ball();

	void init();

	void clear();

	Queue<String> battingOrder(int noOfPlayers);

}
