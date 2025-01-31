package com.tennis.score;

import com.tennis.player.Player;
import com.tennis.player.Team;

public class Score {
	/*
	 * 점수시스템 구현하는부분
	 * 듀스나 어드밴티지 계산로직
	 * 세트나 매치승리조건 구현
	 */
	private Team team1;
	private Team team2;
	private boolean isDeuce; //듀스상태관리
	private boolean gameWon; //게임종료상태관리
	
	// 우승 스코어, 듀스 스코어를 상수로 관리 (코드의 가독성) 
	public static final int WIN_SCORE = 50;
	public static final int DEUCE_SCORE = 40; 

	public Score(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.isDeuce = false;
		this.gameWon = false;
	}

	// 기존의 score 
	public void pointToPlayer(Player player) {
		if (gameWon) {
			System.out.println("게임이 이미 종료되었습니다");
			return;
		}
		player.setScore(player.getScore()+1);;

		for(Player p : team1.getPlayers()) {
			if(p == player) {
				team1.setTeamScore(team1.getTeamScore()+1);;
			}else {
				team2.setTeamScore(team2.getTeamScore()+1);
			}
		}
		updateGameState();
	}
	// 어드밴티지 듀스 승리 모두 구현
	private void updateGameState() {

		int score1 = team1.getTeamScore(); // team1의 점수합계
		int score2 = team2.getTeamScore();
		//승패 관리하는줄 승리점수 도달시 차이가 2 이상이면 무조건 승패 갈림
		if (score1 >= 4 || score2 >= 4) { 
			if (Math.abs(score1 - score2) >= 2) {
				gameWon = true;
				System.out.println("승리 " + (score1 > score2 ? team1.getName() : team2.getName()));
				return;
			}
		}
		//듀스 관리하는 줄 어드밴티지가 발동되면 듀스해제, 어드밴티지이후 다시 동점되면 듀스켜짐
		if (score1 >= 3 && score2 >= 3) {
			if (Math.abs(score1 - score2) == 0) {
				isDeuce = true;
				team1.setAdvantage(false);
				team2.setAdvantage(false);
			} else if (Math.abs(score1 - score2) == 1) {
				isDeuce = false;
				team1.setAdvantage(score1 > score2);
				team2.setAdvantage(score2 > score1);
			} else {
				isDeuce = false;
			}
		} else {
			isDeuce = false;
		}
	}
	// 출력단의 점수 형태 관리
	public String getScore() {
		if (gameWon) {
			return "승리 " + (team1.getTeamScore() > team2.getTeamScore() ? team1.getName() : team2.getName());
		}
		if (isDeuce) {
			return "듀스";
		}
		if (team1.isAdvantage()) {
			return "어드밴티지 " + team1.getName();
		} else if (team2.isAdvantage()) {
			return "어드밴티지 " + team2.getName();
		}
		return formatScore(team1.getTeamScore()) + " - " + formatScore(team2.getTeamScore());
	}
	// 출력단의 점수 형태 관리
	private String formatScore(int score) {
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
			return "";
		}
	}
	public void resetScores() {
		team1.resetTeamScore();
		team2.resetTeamScore();
		isDeuce = false;
		gameWon = false;
	}

	public Team getTeam1() {
		return team1;
	}

	public void setTeam1(Team team1) {
		this.team1 = team1;
	}

	public Team getTeam2() {
		return team2;
	}

	public void setTeam2(Team team2) {
		this.team2 = team2;
	}
	
	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	
}