package com.tennis.player;

public class Player {
	
	// 선수구현부분 
	
	private String name;
	private boolean gender;
	private int score;
	private int game;
	private boolean advantage;
	

	public Player(String name, boolean gender) {
		super();
		this.name = name;
		this.gender = false;
		this.score = 0;
		this.game = 0;
		this.advantage = false;
	}


	
	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public boolean isGender() {
		return gender;
	}


	public void setGender(boolean gender) {
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
	
	
}
