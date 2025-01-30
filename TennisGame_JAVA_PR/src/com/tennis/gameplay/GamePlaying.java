package com.tennis.gameplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tennis.court.CourtManager;
import com.tennis.input.Input;
import com.tennis.player.Player;
import com.tennis.player.Team;
import com.tennis.score.Score;

public class GamePlaying {


	public List<Player> players;
	public int currentSet;
	public int currentGame;

    private CourtManager courtManager = new CourtManager(); // 코트 관리 추가


	public GamePlaying() {
		Input input = new Input();
		String gameType = input.gameTypeSelector(); // 단식 복식 선택
		int gameCount = input.gameCountSelector(gameType); // 게임 성별에 따른 수 선택
		int matchesToWin = (gameCount == 3) ? 2 : 3; //3경기는 2선승 5경기는 3선승
		List<Player> players = input.playerSetting(gameType); // 플레이어 선택 일단은 이름 직접입력하는법으로 적용
		Team[] teamInfo = input.teamSetting(players); // players를 team배열로 반환
		Team team1 = teamInfo[0];// A팀
		Team team2 = teamInfo[1];//B팀
		Score scoreManager = new Score(team1, team2); // 적절하게 초기화
		gamePlay(matchesToWin, scoreManager, gameType);
	}

	// GamePlaying.java

	public void gamePlay(int matchesToWin, Score scoreManager, String gameType) {
		//	    gameUI.printCourt(team1, team2); // team1 team2 정보 출력 (코트상에서)
		int setsWonTeam1 = 0;
		int setsWonTeam2 = 0;
		Team team1 = scoreManager.getTeam1();
		Team team2 = scoreManager.getTeam2();
		Scanner sc = new Scanner(System.in);

		// 추가된 부분 --------------------------------------------------

		//  게임 시작 시 첫 번째 서브 팀 설정
		courtManager.setInitialServer(team1);

		// 추가된 부분 --------------------------------------------------

		while (setsWonTeam1 < matchesToWin && setsWonTeam2 < matchesToWin) {
			// 점수 초기화
			scoreManager.resetScores();

			//이부분에 코트정보 표시하면될듯

			// 세트 진행
			while (!scoreManager.isGameWon()) {
				//	        	chooseGame(team1,team2, scoreManager, sc); // 테스트용 선택해서 점수올리는메서드

				courtManager.updateCourtState(team1, team2); // 코트 위치 및 서브 위치 업데이트

				playGame(team1, team2, scoreManager, gameType); // 실전용 랜덤으로 Player점수 올라가는 메서드
				if (!scoreManager.isGameWon()) {
					printScore(scoreManager.getScore());
					// 사용자가 키를 입력할 때까지 대기 랜덤돌릴땐 아래 주석 풀면됨
					try {
						System.out.println("아무키나 입력");
						System.in.read();
						System.in.skip(System.in.available());
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
			}

			// 수정된 부분 ----------------------------------------------

			//  세트 종료 후 서브 변경
			if (scoreManager.getScore().contains("승리")) {
				if (scoreManager.getScore().contains(team1.getName())) {
					setsWonTeam1++;
				} else {
					setsWonTeam2++;
				}
				System.out.printf("세트 스코어: %d - %d%n", setsWonTeam1, setsWonTeam2);
				scoreManager.resetScores(); 
	
				//  세트가 끝났으므로 서브하는 팀 변경
				courtManager.swapServeAtSetStart(team1, team2);
	
				System.out.println("세트 종료! 다음 서브 진행. 엔터키 입력");
				try {
					System.in.read();
					System.in.skip(System.in.available());
				} catch (IOException e) {
					e.printStackTrace();
				}
			}

			// 수정된 부분 ----------------------------------------------
		}

		
		// 게임 종결 메시지
		System.out.println("경기 종료");
		System.out.printf("최종 승자: %s (%d세트 승리)\n", setsWonTeam1 > setsWonTeam2 ? team1.getName() : team2.getName(), Math.max(setsWonTeam1, setsWonTeam2));
	}
	public void chooseGame(Team team1, Team team2, Score scoreManager, Scanner sc) { // 테스트용 
		String tmp = sc.nextLine();
		System.out.println("A입력 A팀 B입력 B팀 득점 테스트용");
		if(tmp.equals("A")) {
			scoreManager.pointToPlayer(team1.getPlayers().get(0)); 
		}else {
			scoreManager.pointToPlayer(team2.getPlayers().get(0)); 
		}
	}

	public void playGame(Team team1, Team team2, Score scoreManager, String gameType) {
		int rnd = new Random().nextInt(101);
		boolean lineOut = courtManager.isLineOut();
		if (lineOut) {
            System.out.println("라인 아웃! 상대 팀 점수 획득!");
            if (rnd > 50) {
                scoreManager.pointToPlayer(team2.getPlayers().get(0));
            } else {
                scoreManager.pointToPlayer(team1.getPlayers().get(0));
            }
            return;
        }

		if(gameType.equals("단식")) {
			if(rnd > 50) {
				scoreManager.pointToPlayer(team1.getPlayers().get(0));  //A팀 0번플레이어 득점
			}else {
				scoreManager.pointToPlayer(team2.getPlayers().get(0));  // B팀 1번플레이어 득점
			}
		}else {
			if(rnd < 25) {
				scoreManager.pointToPlayer(team1.getPlayers().get(0));//A팀 0번플레이어 득점
			}else if(rnd < 50) {
				scoreManager.pointToPlayer(team1.getPlayers().get(1));//A팀 1번플레이어 득점
			}else if(rnd < 75) {
				scoreManager.pointToPlayer(team2.getPlayers().get(0)); // B팀 0번플레이어 득점
			}else {
				scoreManager.pointToPlayer(team2.getPlayers().get(1)); // B팀 1번플레이어 득점
			}
		}
	}
	private void printScore(String score) {
		System.out.println("\n 점수 :");
		System.out.println(score);
	}
}