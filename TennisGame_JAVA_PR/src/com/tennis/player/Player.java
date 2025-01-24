package com.tennis.player;

public class Player {
	
	// 선수구현부분 
	
	private String name;
	private String gender;
	private int score;
	private int game;
	private int set;
	private boolean advantage;
	

	public Player(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
		this.score = 0;
		this.game = 0;
		this.set = 0;
		this.advantage = false;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public String getGender() {
		return gender;
	}


	public void setGender(String gender) {
		this.gender = gender;
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
