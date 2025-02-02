package Tennis;

import java.io.IOException;
import java.util.Random;

public class Game {
	protected int[] scores = new int[2];
	protected String state = "NORMAL";
	protected int winner;

	public int[] play() {
		Random rnd = new Random();
		int PointWinner;
		do {
			System.out.println("> 아무 키나 입력하여 게임 진행...");
			try {
				System.in.read();
				System.in.skip(System.in.available());
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			PointWinner = rnd.nextInt(10)%2;
			scores[PointWinner]++;
			System.out.println("팀 " + (PointWinner + 1) + " 득점!");
			setState();
			printGameScore();
		} while (!state.equals("END"));
		printWinner();
		return winner == 1 ? new int[]{1,0} : new int[] {0,1};
	}

	protected void printWinner() {
		System.out.printf("\n팀 %d 승리!\n", winner );
		System.out.println("-".repeat(50));
	}

	protected void printGameScore() {
		System.out.println( "팀 1: " + Score.getScoreByValue(scores[0]) +	
				"\n팀 2: " + Score.getScoreByValue(scores[1]) +
				", 상태: " + state);
	}

	public void setState() {
		// 듀스 상태 처리
		if (scores[0] >= 3 || scores[1] >= 3) {
			if (Math.abs(scores[0] - scores[1]) >= 2) {
				if (scores[0] >= 4 || scores[1] >= 4) {
					winner = (scores[0] > scores[1]) ? 1 : 2;  // 승리한 팀 번호 갱신
					state = "END"; // 승리 조건 만족 시 종료
				} else state = "NORMAL";
			} else if (scores[0] == scores[1]) {
				state = "DEUCE"; // 듀스 상태
			} else if (scores[1] >= 3 && scores[0] > scores[1]) {
				state = "ADVANTAGE_TEAM_1"; // 팀1이 AD
			} else if (scores[0] >= 3 && scores[0] < scores[1]){
				state = "ADVANTAGE_TEAM_2"; // 팀2가 AD
			}
		} else {
			// 듀스 이전 상태 (NORMAL)
			state = "NORMAL";
		}
	}
}
