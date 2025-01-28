package com.tennis.player;

public class Player {

	// 선수구현부분 

	private String name;
	private String gender; // 선수 개개인의 성별 
	private int score;  // 선수 개개인의 점수 

	
	public Player(String name) {
		this.name = name;
		this.score = 0;
	}

	public Player(String name, String gender) {
		super();
		this.name = name;
		this.gender = gender;
		this.score = 0;
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
	
	public void resetScore() {
		this.score = 0;
	}
	public void increaseScore() {
		this.score++;
	}
}