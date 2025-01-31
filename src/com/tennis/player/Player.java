package com.tennis.player;

public class Player {

	// 선수 정보 출력

	private String name;
	private String gender; // 선수 개개인의 성별 
	private int score;
	
	public Player(String name, String gender) {
		this.name = name;
		this.gender = gender;
	}
	
	//public Player(String name, String gender) {
	//	this.name = name;
	//	this.gender = gender; 
	//	this.score = 0; // 생성자에서 score을 입력받아 초기화
	//}

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

	public String getScoreText() {
		switch (score) {
		case 0:
			return "0";
		case 1:
			return "15";
		case 2:
			return "30";
		case 3:
			return "40";
		default:
			return "게임"; // 점수가 4 이상일 경우 "게임"을 반환
		}
	}

	@Override
	public String toString() {
		return this.name + " / " + this.gender;
	}
}