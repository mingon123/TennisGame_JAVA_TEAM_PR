package com.tennis.score;

import com.tennis.player.Player;
import com.tennis.player.Team;

public class Score {
	private Team team1;
	private Team team2;
	private boolean isDeuce; 
	private boolean gameWon; 

	public Score(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		this.isDeuce = false;
		this.gameWon = false;
	}

	public boolean isGameWon() {
		return gameWon;
	}

	public void setGameWon(boolean gameWon) {
		this.gameWon = gameWon;
	}

	public void pointToPlayer(Player player) {
		if (gameWon) {
			System.out.println("게임이 이미 종료되었습니다");
			return;
		}
		player.increaseScore();

		for(Player p : team1.getPlayers()) {
			if(p == player) {
				team1.increaseTeamScore();
			}else {
				team2.increaseTeamScore();
			}
		}
		updateGameState();
	}

	private void updateGameState() {

		int score1 = team1.getPlayers().stream().mapToInt(Player::getScore).sum();
		int score2 = team2.getPlayers().stream().mapToInt(Player::getScore).sum();

		if (score1 >= 4 || score2 >= 4) { 
			if (Math.abs(score1 - score2) >= 2) {
				gameWon = true;
				System.out.println("승리 " + (score1 > score2 ? team1.getName() : team2.getName()));
				return;
			}
		}

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

	public String getScore() {
		if (gameWon) {
//			System.out.println(team1.getTeamScore());
//			System.out.println(team2.getTeamScore());
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

	public void resetScores() {
		team1.resetTeamScore();
		team2.resetTeamScore();
		isDeuce = false;
		gameWon = false;
	}
}
