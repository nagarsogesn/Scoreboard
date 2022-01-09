package com.phonepe.scoreboard.service.impl;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

import com.phonepe.scoreboard.constants.BallPossibilities;
import com.phonepe.scoreboard.service.InputService;

public class InputServiceImpl implements InputService {

	private Scanner scanner;

	@Override
	public Integer noOfPlayers() {
		System.out.print("No. of players for each team: ");
		String noOfPlayers = scanner.next();
		while (!isValidNoOfPlayers(noOfPlayers)) {
			System.out.println("Invalid value for no of players. Please re-enter: ");
			noOfPlayers = scanner.next();
		}
		return Integer.valueOf(noOfPlayers);
	}

	private boolean isValidNoOfPlayers(String noOfPlayers) {
		if (isValidInteger(noOfPlayers)) {
			Integer players = Integer.valueOf(noOfPlayers);
			if (players >= 2 && players <= 11) {
				return true;
			}
		}
		return false;
	}

	@Override
	public Integer noOfOvers() {
		System.out.print("No. of overs: ");
		String noOfOvers = scanner.next();
		while (!isValidNoOfOvers(noOfOvers)) {
			System.out.println("Invalid value for no of overs. Please re-enter: ");
			noOfOvers = scanner.next();
		}
		return Integer.valueOf(noOfOvers);
	}

	private boolean isValidNoOfOvers(String noOfOvers) {
		return isValidInteger(noOfOvers);
	}

	@Override
	public String ball() {
		String ballUpdate = scanner.next();
		while (!isValidBallInput(ballUpdate)) {
			System.out.println("Invalid ball update. Please re-enter: ");
			ballUpdate = scanner.next();
		}
		return ballUpdate;
	}

	private static boolean isValidBallInput(String ballUpdate) {
		if (BallPossibilities.isValid(ballUpdate)) {
			return true;
		}
		try {
			int score = Integer.parseInt(ballUpdate);
			if (score >= 1 && score <= 6) {
				return true;
			}
		} catch (NumberFormatException e) {
		}
		return false;
	}

	private boolean isValidInteger(String input) {
		try {
			Integer.parseInt(input);
		} catch (NumberFormatException e) {
			return false;
		}
		return true;
	}

	@Override
	public void init() {
		scanner = new Scanner(System.in);
	}

	@Override
	public void clear() {
		if (null != scanner) {
			scanner.close();
		}
	}

	@Override
	public Queue<String> battingOrder(int noOfPlayers) {
		Queue<String> battingOrder = new LinkedList<>();
		for (int player = 1; player <= noOfPlayers; player++) {
			battingOrder.add(scanner.next());
		}
		return battingOrder;
	}
}
