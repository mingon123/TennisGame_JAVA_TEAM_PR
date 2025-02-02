package Tennis;

import java.io.IOException;

public class Set {
	int[] scores = new int[2];//{5,6}; // 해당 세트의 점수
	private int winner; // 해당 세트의 승리팀
	private boolean tieBreak = false; // 타이브레이크 게임을 해야 하는가
	private boolean isLastSet = false; // 10선 타이브레이크가 필요한 최후의 세트

	public Set(boolean isLastSet) {
		this.isLastSet = isLastSet;
	}
	
	public int[] play() {
		int[] gameResult = null;
		do {
			Game singleGame;
			
			if (isLastSet && tieBreak) singleGame = new TieBreakGame(10); // 최후의 세트면 10선 타이브레이크
			else if (tieBreak) singleGame = new TieBreakGame(7); // 그외 타이브레이크를 해야할 상황이면 7선
			else singleGame = new Game();
			if (singleGame instanceof TieBreakGame) {
				TieBreakGame game = (TieBreakGame) singleGame;
				gameResult = game.play();
			}
			else gameResult = singleGame.play();
			scores[0] += gameResult[0];
			scores[1] += gameResult[1];

			System.out.printf("세트 스코어 - %d : %d\n", scores[0], scores[1]);
		} while ( !tieBreak && !hasWinner());
		if(scores[0] > scores[1]) winner = 1; 
		return winner == 1 ? new int[] {1,0} : new int[] {0,1};
	}
	private boolean hasWinner(){
		if (scores[0] >= 6 || scores[1] >= 6) {
			switch (Math.abs(scores[0] - scores[1])) {
			case 1:
				if(tieBreak) return true;
				return false;
			case 0: 
				tieBreak = true;
				return false;
			default :
				winner = scores[0] > scores[1]? 1: 2;
				return true;
			}
			
		} else 
			return false;
	}
	/*
	private boolean hasWinner(){
		if (scores[0] >= 6 || scores[1] >= 6) {
			if (scores[0] == scores[1]) tieBreak = true;
			if (Math.abs(scores[0] - scores[1]) >= 2) {
				if (scores[0] > scores[1]) {
					winner = 1;
					return true;
				} else {
					winner = 2;
					return true;
				}
			} else return false;
		} else return false;
	}
	*/
}
