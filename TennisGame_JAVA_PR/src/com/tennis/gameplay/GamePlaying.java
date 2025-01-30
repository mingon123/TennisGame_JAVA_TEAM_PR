package com.tennis.gameplay;

import java.io.IOException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.tennis.court.CourtManager;
import com.tennis.gameui.Print;
import com.tennis.input.Input;
import com.tennis.player.Player;
import com.tennis.player.Team;
import com.tennis.score.Score;

public class GamePlaying {

    private CourtManager courtManager; // 코트 관리 추가
    private boolean isDouble; // (추가)
    public GamePlaying() {
        Input input = new Input();

        // 경기 규칙 안내 출력
        Print.printRuleSelect();

        courtManager = new CourtManager(); // 객체 초기화

        String gameType = input.gameTypeSelector(); // 단식 복식 선택
        int gameCount = input.gameCountSelector(gameType); // 게임 성별에 따른 수 선택
        int matchesToWin = (gameCount == 3) ? 2 : 3; // 3경기는 2선승, 5경기는 3선승
        List<Player> players = input.playerSetting(gameType); // 플레이어 선택
        Team[] teamInfo = input.teamSetting(players); // 팀 생성
        Team team1 = teamInfo[0]; // A팀
        Team team2 = teamInfo[1]; // B팀

        // 팀 구성 정보 출력
        Print.printPlayerTeam(team1, team2);

        Score scoreManager = new Score(team1, team2); // 점수 관리

        // 첫 번째 서브 팀 설정
        courtManager.setInitialServer(team1);

        
        // 게임 시작 시 코트 상태 출력 (추가)
        courtManager.printTennisCourt(team1, team2, isDouble);

        // 게임 진행
        gamePlay(matchesToWin, scoreManager, gameType);
    }

    // GamePlaying.java

    public void gamePlay(int matchesToWin, Score scoreManager, String gameType) {
        int setsWonTeam1 = 0;
        int setsWonTeam2 = 0;
        Team team1 = scoreManager.getTeam1();
        Team team2 = scoreManager.getTeam2();
        Scanner sc = new Scanner(System.in);

        while (setsWonTeam1 < matchesToWin && setsWonTeam2 < matchesToWin) {
            // 점수 초기화
            scoreManager.resetScores();

            
            //  게임 시작 시 코트 상태 출력 (추가)
            courtManager.printTennisCourt(team1, team2, isDouble);

            
            // 세트 진행
            while (!scoreManager.isGameWon()) {
                playGame(team1, team2, scoreManager, gameType);

                if (!scoreManager.isGameWon()) {
                    printScore(scoreManager.getScore());

                    // 사용자 입력을 기다려 출력이 잘 보이게 함
                    try {
                        System.out.println("아무 키나 입력하세요.");
                        System.in.read();
                        System.in.skip(System.in.available());
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }

            //  세트 종료 후 처리
            if (scoreManager.getScore().contains("승리")) {
                if (scoreManager.getScore().contains(team1.getName())) {
                    setsWonTeam1++;
                } else {
                    setsWonTeam2++;
                }
                System.out.printf("세트 스코어: %d - %d%n", setsWonTeam1, setsWonTeam2);
                scoreManager.resetScores();

                // 세트 종료 후 서브 변경 (추가)-----------------------------
                courtManager.swapServeAtSetStart(team1, team2);

                //  세트 종료 후 새 코트 상태 출력 (추가)-----------------------------
                Print.printCourtInfo(team1, team2, courtManager.isLeftCourt(), courtManager.isLeftServer());
                
                //  코트 체인지 출력 (추가)-----------------------------
                courtManager.swapCourtPrint(team1, team2);

                System.out.println("세트 종료! 다음 서브 진행. 엔터키 입력");
                try {
                    System.in.read();
                    System.in.skip(System.in.available());
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        //  경기 종료 메시지
        System.out.println("경기 종료");
        System.out.printf("최종 승자: %s (%d세트 승리)\n",
                setsWonTeam1 > setsWonTeam2 ? team1.getName() : team2.getName(),
                Math.max(setsWonTeam1, setsWonTeam2));
    }

    public void playGame(Team team1, Team team2, Score scoreManager, String gameType) {
        int rnd = new Random().nextInt(101);
        boolean lineOut = courtManager.isLineOut();

        //  5% 확률로 라인 아웃 발생
        if (lineOut) {
            System.out.println("라인 아웃! 상대 팀 점수 획득!");
            if (rnd > 50) {
                scoreManager.pointToPlayer(team2.getPlayers().get(0));
            } else {
                scoreManager.pointToPlayer(team1.getPlayers().get(0));
            }
            return;
        }

        // 단식 경기일 경우
        if (gameType.equals("단식")) {
            if (rnd > 50) {
                scoreManager.pointToPlayer(team1.getPlayers().get(0));  // A팀 득점
            } else {
                scoreManager.pointToPlayer(team2.getPlayers().get(0));  // B팀 득점
            }
        } else { 
            //  복식 경기일 경우 (랜덤으로 점수 분배)
            if (rnd < 25) {
                scoreManager.pointToPlayer(team1.getPlayers().get(0)); // A팀 1번 플레이어
            } else if (rnd < 50) {
                scoreManager.pointToPlayer(team1.getPlayers().get(1)); // A팀 2번 플레이어
            } else if (rnd < 75) {
                scoreManager.pointToPlayer(team2.getPlayers().get(0)); // B팀 1번 플레이어
            } else {
                scoreManager.pointToPlayer(team2.getPlayers().get(1)); // B팀 2번 플레이어
            }
        }
    }

    private void printScore(String score) {
        System.out.println("\n 점수 :");
        System.out.println(score);
    }
}
