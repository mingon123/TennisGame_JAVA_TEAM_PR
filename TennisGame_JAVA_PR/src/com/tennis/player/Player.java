package com.tennis.player;


public class Player {
	
	// 선수구현부분 
	
	private String name;
	private String gender; // gender를 따로 입력단에서 걸러내서 받을수있음 일단은 포함
	private int score;
//	private int game;
//	private int set;
//	private boolean advantage; << 팀에서 충분히 구현할수있는 부분
	

	public Player(String name) {
		super();
		this.name = name;
//		this.gender = gender;
		this.score = 0; // 저는 개인득점수로 구현 Team의 score는 
//		this.game = 0;
//		this.set = 0;
//		this.advantage = false;
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
	
	public void increaseScore() {
		this.score++;
	} // 점수올리기 쉽게 메서드 작성 (클래스다이어그램에 일치하도록 뺴도됨)


//	public int getGame() {
//		return game;
//	}
//
//
//	public void setGame(int game) {
//		this.game = game;
//	}
//	public boolean isAdvantage() {
//		return advantage;
//	}
//	public void setAdvantage(boolean advantage) {
//		this.advantage = advantage;
//	}
//	public int getSet() {
//		return set;
//	}
//
//	public void setSet(int set) {
//		this.set = set;
//	}

	

	
}