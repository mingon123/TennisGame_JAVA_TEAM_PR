package Tennis;

import java.util.Random;

public class TieBreakGame extends Game{
	private int gameCount;
	private int needWin;


	public TieBreakGame(int count) {
		System.out.println("타이브레이크");
		this.gameCount = count;
		this.needWin = count - 1;
	}

	@Override
	public void printGameScore() {
		System.out.println( "(타이브레이크)\n팀 1: " + scores[0] +
				"\n팀 2: " + scores[1]);
	}

	@Override
	public void setState() {
		// 듀스 상태 처리
		if (scores[0] >= needWin || scores[1] >= needWin) {
			if (Math.abs(scores[0] - scores[1]) >= 2) {
				if (scores[0] >= gameCount || scores[1] >= gameCount) {
					winner = (scores[0] > scores[1]) ? 1 : 2;  // 승리한 팀 번호 갱신
					state = "END"; // 승리 조건 만족 시 종료
				}
				else state = "NORMAL";
			} else if (scores[0] == scores[1]) {
				state = "DEUCE"; // 듀스 상태
			} else if (scores[1] >= needWin && scores[0] > scores[1]) {
				state = "ADVANTAGE_TEAM_1"; // 팀1이 AD
			} else if (scores[0] >= needWin && scores[0] < scores[1]){
				state = "ADVANTAGE_TEAM_2"; // 팀2가 AD
			}
		} else {
			// 듀스 이전 상태 (NORMAL)
			state = "NORMAL";
		}
	}



}
