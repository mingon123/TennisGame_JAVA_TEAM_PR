package com.tennis.gameplay;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tennis.player.Player;
import com.tennis.player.Team;
import com.tennis.score.Score;

public class GamePlaying {


	private static Score scoreManager;
	public List<Player> players;
	public int currentSet;
	public int currentGame;

	public GamePlaying(){
		Scanner sc = new Scanner(System.in);
		String gameType = gameTypeSelector(sc); // 단식 복식 선택
		int gameCount = gameCountSelector(gameType, sc); // 게임 성별에 따른 수 선택
		int matchesToWin = (gameCount == 3) ? 2 : 3;
		List<Player> players = playerSetting(gameType, sc); // 플레이어 선택 일단은 이름 직접입력하는버ㅓㅈㄴ
		Team[] teamInfo = teamSetting(players); // players를 team배열로 반환
		Team team1 = teamInfo[0];// A팀
		Team team2 = teamInfo[1];//B팀
		scoreManager = new Score(team1, team2, matchesToWin); // 적절하게 초기화
		playGame(teamInfo[0], teamInfo[1], sc, matchesToWin); // 게임 시작
	}

	private List<Player> playerSetting(String gameType, Scanner sc) {
		List<Player> result = new ArrayList<>();
		if(gameType.contains("복식")) {
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
		}else {
			System.out.println("A팀 1번 선수의 이름을 입력해주세요");
			String name1 = sc.nextLine();
			result.add(new Player(name1));
			System.out.println("B팀 1번 선수의 이름을 입력해주세요");
			String name2 = sc.nextLine();
			result.add(new Player(name2));
			return result;
		}
	}

	private int gameCountSelector(String gameType, Scanner sc) {
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
	                    if(count == 5) {
	                        return 5; 
	                    }
	                    return count; // 3경기에서 2세트 승리
	                }
	            }
	        } else { // 여자
	            System.out.println("여자 단식 경기는 3경기 단일 경기로 진행됩니다");
	            return 3; // 3경기에서 2세트 승리
	        }
	    } else if (gameType.equals("복식")) {
	        System.out.println("경기 방식 : 복식");
	        String gender = genderSelector(sc);
	        return 3; // 여자는 복식에서 3경기 단일 경기 진행
	    } else if (gameType.equals("혼합복식")) {
	    	System.out.println("혼합 복식 경기는 3경기 단일 경기로 진행됩니다");
            return 3; // 3경기
	    }
	    return -1;
	}

	private String genderSelector(Scanner sc) {
		String gender;
		while(true) {
			System.out.println("참가하는 선수의 성별을 선택해 주십시오 (남/녀)");
			gender = sc.nextLine();
			if(gender.equals("남") || gender.equals("녀")) break;
		}
		return gender;
	}
	
	private String gameTypeSelector(Scanner sc) {
		String gameType;
		while(true) {
			System.out.println("단식 / 복식 / 혼합복식 을 선택해 주십시오 ");
			gameType = sc.nextLine();
			if(gameType.equals("단식") || gameType.equals("복식") || gameType.equals("혼합복식"))
				break;
			System.out.println("잘못된 입력입니다.");
		}
		return gameType;
	}
	private Team[] teamSetting( List<Player> players) {
		int length = players.size();
		Team[] teams = new Team[2];
		List<Player> listA = new ArrayList<>();
		List<Player> listB = new ArrayList<>();
		if(length == 2) {
			listA = players.subList(0, 1);
			listB = players.subList(1, 2);
		}else if(players.size() == 4) {
			listA = players.subList(0, 2);
			listB = players.subList(2, 4);
		}
		teams[0] = new Team(listA);
		teams[1] = new Team(listB);
		return teams;
	}

	private void playGame(Team team1, Team team2, Scanner sc, int matchesToWin) {
        System.out.println("경기를 시작합니다!");
        while (true) {
            System.out.println("포인트를 득점한 팀을 입력하세요 (A/B):");
            String input = sc.nextLine().toUpperCase();
            // 여기서 적절하게 출력해주면 됨 출력부분은 아예 뻇음
            if (input.equals("A")) {
                scoreManager.score(team1);
                checkGameProgress(team1, team2);
            } else if (input.equals("B")) {
                scoreManager.score(team2);
                checkGameProgress(team1, team2);
            } else {
                System.out.println("잘못된 입력입니다. 다시 입력해주세요.");
            }

            if (scoreManager.matchWinner()) {
                System.out.println("경기가 종료되었습니다!");
                if (team1.getSet() == matchesToWin) {
                    System.out.println("A팀이 승리했습니다!");
                } else {
                    System.out.println("B팀이 승리했습니다!");
                }
                break;
            }
        }
    }

    private void checkGameProgress(Team team1, Team team2) {
        if (scoreManager.gameWinner(team1)) {
            team1.setGame(team1.getGame() + 1);
            System.out.println("A팀이 게임을 승리했습니다!");
        } else if (scoreManager.gameWinner(team2)) {
            team2.setGame(team2.getGame() + 1);
            System.out.println("B팀이 게임을 승리했습니다!");
        }

        if (scoreManager.setWinner()) {
            if (team1.getSet() > team2.getSet()) {
                System.out.println("A팀이 세트를 승리했습니다!");
            } else {
                System.out.println("B팀이 세트를 승리했습니다!");
            }
        }
    }
//	private void playGame(Team team1, Team team2, Score scoreManager, String gameType) {
//		int rnd = new Random().nextInt(101);
//		if(gameType.equals("단식")) {
//			if(rnd > 50) {
//				scoreManager.pointToPlayer(team1.getPlayers().get(0)); 
//			}else {
//				scoreManager.pointToPlayer(team2.getPlayers().get(0)); 
//			}
//		}else {
//			if(rnd < 25) {
//				scoreManager.pointToPlayer(team1.getPlayers().get(0));
//			}else if(rnd < 50) {
//				scoreManager.pointToPlayer(team1.getPlayers().get(1));
//			}else if(rnd < 75) {
//				scoreManager.pointToPlayer(team2.getPlayers().get(0)); 
//			}else {
//				scoreManager.pointToPlayer(team2.getPlayers().get(1)); 
//			}
//		}
//	}
	// 랜덤으로 승자 구현할때 코드 

}
