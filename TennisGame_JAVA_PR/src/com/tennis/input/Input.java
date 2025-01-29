package com.tennis.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.tennis.player.Player;
import com.tennis.player.Team;

public class Input {
	
	Scanner sc = new Scanner(System.in);
	
	/*
	 * 입력값 처리하는부분 Scanner 등으로 문자열 반환해주는 부분
	 */
	 public List<Player> playerSetting(String gameType) { // 일단 선수이름을 직접입력받는 형태로 구현해놨음
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
	 
	 public int gameCountSelector(String gameType) { // 경기수 선택받는 라인
	    	if (gameType.equals("단식")) {
	    		System.out.println("경기 방식 : 단식");
	    		String gender = genderSelector();
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
	    		String gender = genderSelector();
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
	 
	 public String genderSelector() { // 성별타입 입력받는라인
	    	String gender;
	    	while (true) {
	    		System.out.println("참가하는 선수의 성별을 선택해 주십시오 (남/녀)");
	    		gender = sc.nextLine();
	    		if (gender.equals("남") || gender.equals("녀"))
	    			break;
	    	}
	    	return gender;
	    }
	 public String gameTypeSelector() { // 게임타입 입력받는라인
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
	 
	 public Team[] teamSetting(List<Player> players) { // players List를 를 Team 배열로 반환
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
