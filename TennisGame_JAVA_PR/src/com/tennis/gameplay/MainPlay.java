package com.tennis.gameplay;

import com.tennis.player.Player;
import com.tennis.player.Team;

public class MainPlay {
	
	public static void main(String[] args) {
		
		Team team = new Team(); // 팀 클래스 객체 생성입니다 ! 플레이어 클래스에서 선언만하고 팀 클래스에서 함수 만들었습니다!
		
		team.addPlayer(new Player("양희동" , "남"));  // 예시입니다! 나머지도 이런식으로 넣어주시면 됩니다!
		
		GamePlaying.gameStart();
	}

}
