package com.phonepe.scoreboard.constants;

public interface ScoreCardTitleWidth {

	int PADDING_RIGHT = 5;
	int PLAYER_NAME = ScoreCardTitles.PLAYER_NAME.length() + PADDING_RIGHT;
	int SCORE = ScoreCardTitles.SCORE.length() + PADDING_RIGHT;;
	int FOURS = ScoreCardTitles.FOURS.length() + PADDING_RIGHT;;
	int SIXES = ScoreCardTitles.SIXES.length() + PADDING_RIGHT;;
	int BALLS = ScoreCardTitles.BALLS.length() + PADDING_RIGHT;
	int STRIKE_RATE = ScoreCardTitles.STRIKE_RATE.length() + PADDING_RIGHT;
	int TOTAL_WIDTH = PLAYER_NAME + SCORE + FOURS + SIXES + BALLS + STRIKE_RATE + (7*2);

}
