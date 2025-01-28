package com.tennis.player;


import java.util.List;

public class Team {

//	private Player player1;
//	private Player player2;
	private List<Player> players; // 플레이어의 수는 단식, 복식마다 다름 List로 관리한다면 1명 2명 모두 유동적으로 관리할수있음
	private int score; // 팀의 종합 스코어 팀의 Player 득점수를 모두 합쳐서 나타내면 됨
	private int game;
	private int set;
	private boolean advantage;
	
	
	public Team(List<Player> players) {
//		this.player1 = player1;
//		this.player2 = player2;
		this.players = players;
		this.score = 0;
		this.game = 0;
		this.set = 0;
		this.advantage = false;	
	}

	public List<Player> getPlayers() {
		return players;
	}




	public void setPlayers(List<Player> players) {
		this.players = players;
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
	public void addPlayer(String name) {
		this.players.add(new Player(name));
	}
	
	public void increaseTeamScore() {
		this.score++;
	} // team득점 올리기 편하게 구현해놓음 Score에서 통합가능 ㅇㅇ
	public void resetTeamScore() {
		this.score = 0;
		this.advantage = false;
		for (Player player : players) {
            player.setScore(0);
        }
	} // team 점수 리셋하기편하게 구현해놓음 Score에서 통합가능 ㅇㅇ
}