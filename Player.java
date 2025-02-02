package project1;

import java.util.Scanner;

class Player {
	private final Scanner scanner;

	public Player() {
		this.scanner = new Scanner(System.in);
	}

	public int getValidatedInput(String prompt, int... validInputs) {
		while (true) {
			System.out.print(prompt);
			if (scanner.hasNextInt()) {
				int input = scanner.nextInt();
				scanner.nextLine();
				for (int valid : validInputs) {
					if (input == valid) {
						return input;
					}
				}
			}
			System.out.println("잘못된 입력입니다. 다시 시도해주세요.");
			scanner.nextLine(); // 잘못된 입력 처리
		}
	}

	public String[][] getPlayerNames(int gameType) {
		if (gameType == 1) {
			// 단식: 이름 2개 입력
			System.out.print("플레이어 1의 이름을 입력하세요: ");
			String player1 = scanner.nextLine();

			System.out.print("플레이어 2의 이름을 입력하세요: ");
			String player2 = scanner.nextLine();

			return new String[][] { { player1 }, { player2 } };
		} else if (gameType == 2) {
			// 복식: 이름 4개 입력
			System.out.print("팀 1의 첫 번째 플레이어 이름을 입력하세요: ");
			String team1Player1 = scanner.nextLine();

			System.out.print("팀 1의 두 번째 플레이어 이름을 입력하세요: ");
			String team1Player2 = scanner.nextLine();

			System.out.print("팀 2의 첫 번째 플레이어 이름을 입력하세요: ");
			String team2Player1 = scanner.nextLine();

			System.out.print("팀 2의 두 번째 플레이어 이름을 입력하세요: ");
			String team2Player2 = scanner.nextLine();

			return new String[][] { { team1Player1, team1Player2 }, { team2Player1, team2Player2 } };
		}
		return new String[0][0];
	}

	public void closeScanner() {
		scanner.close();
	}
}