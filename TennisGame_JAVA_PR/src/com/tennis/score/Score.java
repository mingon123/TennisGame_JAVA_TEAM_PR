package com.tennis.score;

import com.tennis.player.Team;

public class Score {
	/*
	 * 
	 * 점수시스템 구현하는부분
	 * 듀스나 어드밴티지 계산로직
	 * 세트나 매치승리조건 구현
	 * 
	 */

	private Team team1;
	private Team team2;
	private int match;
	
	public Score(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.match = match;
	}
	

	// 점수
	public void score(Team winner) {
		if (winner.getScore() == 0) winner.setScore(15);
		else if (winner.getScore() == 15) winner.setScore(30);
		else if (winner.getScore() == 30) winner.setScore(40);
		else if (winner.getScore() == 40) {
			if(deuce()) {
				advantage(winner);
			} else {
				winner.setScore(50);
			}
		}
	}

	
	// 어드밴티지
	public void advantage(Team winner) {
		if (deuce()) {
			if(team1.equals(winner)) {
				team1.setAdvantage(true);
				team2.setAdvantage(false);
			} else if (team2.equals(winner)){
				team2.setAdvantage(true);
				team1.setAdvantage(false);
			}
		} else {
			if (team1.isAdvantage() && team2.equals(winner)){
				team1.setAdvantage(false);
			} else if (team2.isAdvantage() && team1.equals(winner)){
				team2.setAdvantage(false);
			}
		}
	}

	
	// 듀스
	public boolean deuce() {
		return team1.getScore() == 40 && team2.getScore() == 40 && !team1.isAdvantage() && !team2.isAdvantage(); 
	}
	
	
	// 게임승리
	public boolean gameWinner(Team winner) {
		if(deuce()) {
			return false;
		} else if (winner.getScore() == 50){
			scoreReset();
			return true;	
		}
		return false;
	}
	
	
	// 세트승리
	public boolean setWinner(int winner) {
		if (team1.getGame() >= 6 && team1.getGame() - team2.getGame() >=2 ) {
			team1.setSet(team1.getSet() +1 );
			scoreReset();
			gameReset();
			return true;
		}
		if (team2.getGame() >= 6 && team2.getGame() - team1.getGame() >=2 ) {
			team2.setSet(team2.getSet() +1 );
			scoreReset();
			gameReset();
			return true;
		}
		return false;
	}
 	
	
	// 매치승리
	public boolean matchWinner() {
		if(team1.getSet() == match) {
			scoreReset();
			gameReset();
			setReset();
			return true;
		}
		if (team2.getSet() == match) {
			scoreReset();
			gameReset();
			setReset();
			return true;
		}
		return false;
	}
	
	
	// 초기화
	public void scoreReset() {
		team1.setScore(0);
		team2.setScore(0);
	}
	
	public void gameReset() {
		team1.setGame(0);
		team2.setGame(0);		
	}
	
	public void setReset() {
		team1.setSet(0);
		team2.setSet(0);
	}
	
	
}
