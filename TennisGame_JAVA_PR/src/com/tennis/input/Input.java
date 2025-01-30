package com.tennis.input;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import com.tennis.gameplay.GamePlaying;
import com.tennis.gameui.Print;
import com.tennis.player.Player;
import com.tennis.player.Team;

public class Input {
    
    /*
    * 입력값 처리하는부분 Scanner 등으로 문자열 반환해주는 부분
    * 
    * 
    * 
    */

    Scanner sc = new Scanner(System.in);
    private static final List<Player> malePlayers = new ArrayList<>();
    private static final List<Player> femalePlayers = new ArrayList<>();

    static {
        // 남자 선수 목록
        malePlayers.add(new Player("권용범", "남"));
        malePlayers.add(new Player("김대원", "남"));
        malePlayers.add(new Player("김민곤", "남"));
        malePlayers.add(new Player("박세훈", "남"));
        malePlayers.add(new Player("박정현", "남"));
        malePlayers.add(new Player("양희동", "남"));
        malePlayers.add(new Player("오재문", "남"));
        malePlayers.add(new Player("윤형식", "남"));
        malePlayers.add(new Player("이찬희", "남"));
        malePlayers.add(new Player("주정호", "남"));

        // 여자 선수 목록
        femalePlayers.add(new Player("김하은", "여"));
        femalePlayers.add(new Player("라일락", "여"));
        femalePlayers.add(new Player("박예진", "여"));
        femalePlayers.add(new Player("유예나", "여"));
        femalePlayers.add(new Player("최지우", "여"));
    }

    public static List<Player> getMalePlayers() {
        return new ArrayList<>(malePlayers);
    }

    public static List<Player> getFemalePlayers() {
        return new ArrayList<>(femalePlayers);
    }

       
   
   /*
    * 입력값 처리하는부분 Scanner 등으로 문자열 반환해주는 부분
    */
    public List<Player> playerSetting(String gameType) {
       
       // 선수 선택 화면 출력
        Print.printPlayerSelect();
        
        
        List<Player> selectedPlayers = new ArrayList<>();
        List<Player> malePlayers = getMalePlayers();
        List<Player> femalePlayers = getFemalePlayers();
    
   if (gameType.contains("복식")) {
        System.out.println("남자 복식(1) / 여자 복식(2) / 혼합 복식(3) 중 하나를 선택하세요:");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) { // 남자 복식
            selectedPlayers.add(selectPlayer(malePlayers, "A팀 1번"));
            selectedPlayers.add(selectPlayer(malePlayers, "A팀 2번"));
            selectedPlayers.add(selectPlayer(malePlayers, "B팀 1번"));
            selectedPlayers.add(selectPlayer(malePlayers, "B팀 2번"));
        } else if (choice == 2) { // 여자 복식
            selectedPlayers.add(selectPlayer(femalePlayers, "A팀 1번"));
            selectedPlayers.add(selectPlayer(femalePlayers, "A팀 2번"));
            selectedPlayers.add(selectPlayer(femalePlayers, "B팀 1번"));
            selectedPlayers.add(selectPlayer(femalePlayers, "B팀 2번"));
        } else if (choice == 3) { // 혼합 복식
            selectedPlayers.add(selectPlayer(malePlayers, "A팀 남자"));
            selectedPlayers.add(selectPlayer(femalePlayers, "A팀 여자"));
            selectedPlayers.add(selectPlayer(malePlayers, "B팀 남자"));
            selectedPlayers.add(selectPlayer(femalePlayers, "B팀 여자"));
        }
    } else { // 단식 경기
        System.out.println("남자 단식(1) / 여자 단식(2) 중 하나를 선택하세요:");
        int choice = Integer.parseInt(sc.nextLine());

        if (choice == 1) {
            selectedPlayers.add(selectPlayer(malePlayers, "A팀"));
            selectedPlayers.add(selectPlayer(malePlayers, "B팀"));
        } else {
            selectedPlayers.add(selectPlayer(femalePlayers, "A팀"));
            selectedPlayers.add(selectPlayer(femalePlayers, "B팀"));
        }
    }

    return selectedPlayers;
}
private Player selectPlayer(List<Player> playerList, String role) {
   
   //  선수 선택 화면 출력
    Print.printPlayerSelect();  
    System.out.println(role + " 선수를 선택하세요:");
    for (int i = 0; i < playerList.size(); i++) {
        System.out.printf("%d. %s\n", i + 1, playerList.get(i).getName());
    }
    int choice = Integer.parseInt(sc.nextLine()) - 1;
    return playerList.get(choice);
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
