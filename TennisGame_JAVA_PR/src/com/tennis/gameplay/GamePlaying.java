package com.tennis.gameplay;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tennis.player.Player;
import com.tennis.player.Team;
import com.tennis.score.Score;

public class GamePlaying {

 
    public List<Player> players;
    public int currentSet;
    public int currentGame;

    public GamePlaying() {
        Scanner sc = new Scanner(System.in);
        String gameType = gameTypeSelector(sc); // 단식 복식 선택
        int gameCount = gameCountSelector(gameType, sc); // 게임 성별에 따른 수 선택
        int matchesToWin = (gameCount == 3) ? 2 : 3; //3경기는 2선승 5경기는 3선승
        List<Player> players = playerSetting(gameType, sc); // 플레이어 선택 일단은 이름 직접입력하는법으로
        Team[] teamInfo = teamSetting(players); // players를 team배열로 반환
        Team team1 = teamInfo[0];// A팀
        Team team2 = teamInfo[1];//B팀
        Score scoreManager = new Score(team1, team2); // 적절하게 초기화
        start(matchesToWin, scoreManager, gameType);
    }

 // GamePlaying.java
    
    public void start(int matchesToWin, Score scoreManager, String gameType) {
//	    gameUI.printCourt(team1, team2); // team1 team2 정보 출력 (코트상에서)
	    int setsWonTeam1 = 0;
	    int setsWonTeam2 = 0;
	    Team team1 = scoreManager.getTeam1();
	    Team team2 = scoreManager.getTeam2();
	    Scanner sc = new Scanner(System.in);
	    

	    while (setsWonTeam1 < matchesToWin && setsWonTeam2 < matchesToWin) {
	        // 점수 초기화
	        scoreManager.resetScores();

	        // 세트 진행
	        while (!scoreManager.isGameWon()) {
//	        	chooseGame(team1,team2, scoreManager, sc); // 테스트용 선택해서 점수올리는메서드
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

	        // 승리한 팀 계산
	        if (scoreManager.getScore().contains("승리")) {
	            if (scoreManager.getScore().contains(team1.getName())) {
	                setsWonTeam1++;
	            } else {
	                setsWonTeam2++;
	            }
	            System.out.printf("세트 스코어: %d - %d%n", setsWonTeam1, setsWonTeam2);
	            scoreManager.resetScores(); // 다음 세트를 위해 점수 초기화
	            System.out.println("세트 종료 엔터키 입력");
                try {
                	System.in.read();
					System.in.skip(System.in.available());
				} catch (IOException e) {
					e.printStackTrace();
				}
	        }
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
    
    private List<Player> playerSetting(String gameType, Scanner sc) { // 일단 선수이름을 직접입력받는 형태로 구현해놨음
    	List<Player> result = new ArrayList<>();
    	if (gameType.contains("복식")) {
    		System.out.println("A팀 1번 선수의 이름을 입력해주세요");
    		String name1 = sc.nextLine();
    		result.add(new Player(name1));
    		System.out.println("A팀 2번 선수의 이름을 입력해주세요");
    		String name2 = sc.nextLine();
    		result.add(new Player(name2));
    		System.out.println("B팀 1번 선수의 이름을 입력해주세요");
    		String name3 = sc.nextLine();
    		result.add(new Player(name3));
    		System.out.println("B팀 2번 선수의 이름을 입력해주세요");
    		String name4 = sc.nextLine();
    		result.add(new Player(name4));
    		return result;
    	} else {
    		System.out.println("A팀 1번 선수의 이름을 입력해주세요");
    		String name1 = sc.nextLine();
    		result.add(new Player(name1));
    		System.out.println("B팀 1번 선수의 이름을 입력해주세요");
    		String name2 = sc.nextLine();
    		result.add(new Player(name2));
    		return result;
    	}
    }
    
    private int gameCountSelector(String gameType, Scanner sc) { // 경기수 선택받는 라인
    	if (gameType.equals("단식")) {
    		System.out.println("경기 방식 : 단식");
    		String gender = genderSelector(sc);
    		if (gender.equals("남")) {
    			System.out.println("3경기 / 5경기 중 하나를 선택해 주십시오");
    			int count;
    			while (true) {
    				count = Integer.parseInt(sc.nextLine());
    				if (count == 3 || count == 5) {
    					System.out.printf("%d경기 선택되었습니다\n", count);
    					if (count == 5) {
    						return 5;
    					}
    					return count; // 3경기에서 2세트 승리
    				}
    			}
    		} else { // 여자
    			System.out.println("여자 단식 경기는 3경기로 진행됩니다");
    			return 3; // 3경기에서
    		}
    	} else if (gameType.equals("복식")) {
    		System.out.println("경기 방식 : 복식");
    		String gender = genderSelector(sc);
    		if(gender.equals("남")) { // 이부분 최적화 가능
    			System.out.println("3경기 / 5경기 중 하나를 선택해 주십시오");
    			int count;
    			while (true) {
    				count = Integer.parseInt(sc.nextLine());
    				if (count == 3 || count == 5) {
    					System.out.printf("%d경기 선택되었습니다\n", count);
    					if (count == 5) {
    						return 5;
    					}
    					return count; // 3경기에서 2세트 승리
    				}
    			}
    		}else {
    			System.out.println("여자 복식 경기는 3경기로 진행됩니다");
    			return 3; // 여자는 복식에서 3경기 단일 경기 진행
    		}
    	} else if (gameType.equals("혼합복식")) {
    		System.out.println("혼합 복식 경기는 3경기 단일 경기로 진행됩니다");
    		return 3; // 3경기
    	}
    	return -1;
    }
    
    private String genderSelector(Scanner sc) { // 성별타입 입력받는라인
    	String gender;
    	while (true) {
    		System.out.println("참가하는 선수의 성별을 선택해 주십시오 (남/녀)");
    		gender = sc.nextLine();
    		if (gender.equals("남") || gender.equals("녀"))
    			break;
    	}
    	return gender;
    }
    
    private String gameTypeSelector(Scanner sc) { // 게임타입 입력받는라인
    	String gameType;
    	while (true) {
    		System.out.println("단식 / 복식 / 혼합복식 을 선택해 주십시오 ");
    		gameType = sc.nextLine();
    		if (gameType.equals("단식") || gameType.equals("복식") || gameType.equals("혼합복식"))
    			break;
    		System.out.println("잘못된 입력입니다.");
    	}
    	return gameType;
    }
    
    private Team[] teamSetting(List<Player> players) { // players List를 를 Team 배열로 반환
    	int length = players.size();
    	Team[] teams = new Team[2];
    	List<Player> listA = new ArrayList<>();
    	List<Player> listB = new ArrayList<>();
    	if (length == 2) {
    		listA = players.subList(0, 1);
    		listB = players.subList(1, 2);
    	} else if (players.size() == 4) {
    		listA = players.subList(0, 2);
    		listB = players.subList(2, 4);
    	}
    	teams[0] = new Team("A", listA);
    	teams[1] = new Team("B", listB);
    	return teams;
    }
}
