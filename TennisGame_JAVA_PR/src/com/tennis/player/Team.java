package com.tennis.player;

public class Team {

	private Player player1;
	private Player player2;
	private int score;
	private int game;
	private int set;
	private boolean advantage;
	
	
	public Team(Player player1, Player player2) {
		this.player1 = player1;
		this.player2 = player2;
		this.score = 0;
		this.game = 0;
		this.set = 0;
		this.advantage = false;	
	}

	
	public Player getPlayer1() {
		return player1;
	}

	public void setPlayer1(Player player1) {
		this.player1 = player1;
	}

	public Player getPlayer2() {
		return player2;
	}

	public void setPlayer2(Player player2) {
		this.player2 = player2;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public int getGame() {
		return game;
	}

	public void setGame(int game) {
		this.game = game;
	}

	public boolean isAdvantage() {
		return advantage;
	}

	public void setAdvantage(boolean advantage) {
		this.advantage = advantage;
	}

	public int getSet() {
		return set;
	}
	
	public void setSet(int set) {
		this.set = set;
	}


	
}
