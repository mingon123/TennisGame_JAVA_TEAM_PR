package com.tennis.player;

import java.util.ArrayList;
import java.util.List;

public class Team {

	private List<Player> players;
    private static Team team1;
    private static Team team2;
    private int game;
	private int set;
	private int score;
	private boolean advantage;

 
	public Team() {
		this.players = new ArrayList<>();
		this.game = 0;
		this.set = 0;
		this.score = 0;
		this.advantage = false; 
		
	}
	
	public void addPlayer(Player player) {
		players.add(player);
	}

	public Player getPlayer(String name){ // 특정 선수 정보를 조회하기 위한 함수

		for(Player player : players)  // 모든 선수 탐색
		{
			if(player.getName().equals(name)){ // 아름과 일치하는 선수 확인
				return player; // 맞으면 반환
			}
		}
		return null;
	}
	
	
	public String getPlayerNames() { // 선수의 이름 가져오기
        StringBuilder names = new StringBuilder();
        for (Player player : players) { // 선수 이름 탐색
            names.append(player.getName()).append(" "); // 선수 이름 추가
        }
        return names.toString().trim(); // 마지마 공백 제거하고 반환
    }

	public void printPlayer() {
		System.out.println("선수 목록 : ");
		for(Player player : players) {
			System.out.println(players);
		}
		
	}
	
	
	

	public void setAdvantage(boolean advantage) {
		// TODO Auto-generated method stub
		this.advantage = advantage;
		
	}
	public boolean isAdvantage() {
		// TODO Auto-generated method stub
		return advantage;
	}
	public void setGame(int game) {
		// TODO Auto-generated method stub
		this.game = game;
		
	}
	public void setSet(int set) {
		// TODO Auto-generated method stub
		this.set = set;
		
	}
	public int getSet() {
		// TODO Auto-generated method stub
		return set;
	}
	public int getGame() {
		// TODO Auto-generated method stub
		return game;
	}
    

	public void setScore(int score){
		this.score = score;
		
	}

	public int getScore(){
		return score;
	}

	

}
