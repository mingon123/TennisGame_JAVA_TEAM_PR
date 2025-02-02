package Tennis;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import java.util.function.Predicate;

public class Match {

	static boolean isSingle = false; // 단식 / 복식
	static MatchGender mode = MatchGender.FEMALE; // 남/여/혼합
	static int setCount = 0; // 총 세트 수(남: 5,3세트 선택/ 그외: 3세트 고정)
	static int needWin = 0; // 이겨야 하는 세트 수 - 1(총 세트 수 절반 이상)
	static Team[] teams = {new Team(1), new Team(2)}; // 경기를 뛸 선수를 팀으로 구성(1,2인)
	static int[] scores = new int[2];//{1.1}; // 경기 점수 
	static Scanner scanner = new Scanner(System.in);
	static String choose = null;
	static boolean wrongAnswer = false;
	static final String FILE_NAME = "C:\\Users\\bbb\\Desktop\\tennis\\players.txt"; 
	
	public static void main(String[] args) {
		
		setIsSingle();
		setTeam();
		setSetCount();
		int[] setResult; // 하나의 세트 진행 결과
		Set singleSet; // 하나의 세트
		do {
			if (scores[0] == needWin && scores[0] == scores[1]) singleSet = new Set(true); // 마지막 세트 - 타이브레이크 게임
			else singleSet = new Set(false);
			setResult = singleSet.play();
			scores[0] += setResult[0];
			scores[1] += setResult[1];
			System.out.printf("매치 스코어 - %d : %d\n", scores[0], scores[1]);
		} while (scores[0] <= needWin && scores[1] <= needWin);
		System.out.printf("***팀 %d 최종 승리!!!***", scores[0] > scores[1] ? 1 : 2);
	}
	
	// 경기 종류에 따라 세트 수 결정
	private static void setSetCount() {
		if ( mode == MatchGender.MALE) { // 남자 단식,복식에서만 5,3 세트 선택
			System.out.println("> 세트 수 선택  (5 / 3)");
			do {
				if (wrongAnswer) System.out.println("> 입력 오류 다시 입력");
				choose = scanner.next();
				wrongAnswer = true;
			} while (!choose.matches("5|3"));
			wrongAnswer = false;
			setCount = Integer.parseInt(choose);
		}
		else setCount = 3;
		needWin = setCount / 2;
	}
	
	// 단/복식 입력
	public static void setIsSingle() {
		do {	
			if (wrongAnswer) System.out.println("> 입력 오류 다시 입력");
			System.out.println("> 단식 / 복식 (단/복) 선택 : ");
			choose = scanner.next();
			wrongAnswer = true;
		} while (!choose.matches("단식|복식|단|복"));
		wrongAnswer = false;
		isSingle =  choose.matches("단식|단") ? true : false;
	}
	
	// 파일로부터 선수를 읽어와 팀을 구성
	public static void setTeam() {
		do {
			if (wrongAnswer) System.out.println("> 입력 오류 다시 입력");
			System.out.println("> 남자/여자" + (!isSingle ? "/혼합: " : ": "));
			choose = scanner.next();
			wrongAnswer = true;
		} while (!choose.matches("남자|여자|혼합|남|여|혼"));
		wrongAnswer = false;
		List<String[]> playerList = null; // 파일에서 읽어들인 선수들의 정보를 담을 리스트
		switch (choose) {
		case "남": case "남자":
			playerList = readPlayerDataFromFile(FILE_NAME, "M");
			mode = MatchGender.MALE;
			break;
		case "여": case "여자":
			playerList = readPlayerDataFromFile(FILE_NAME, "F");
			mode = MatchGender.FEMALE;
			break;
		default:
			playerList = readPlayerDataFromFile(FILE_NAME, "MIX");
			mode = MatchGender.MIXED;
			break;
		}
		
		System.out.println("> 선수 선택 ");
		for (String[] player : playerList) {
			System.out.printf("%s (%s) ",player[0],player[1]);
		}
		System.out.print("\n: ");
		
		for (int i = 0; i < teams.length; i++) {
			System.out.printf("팀%d\n",i+1);
			do {
				do {
					choose = scanner.next();
				} while (!checkPlayer(playerList, choose) // 유효성 검사 후, 선수 객체를 생성하여 팀에 투입
						|| !teams[i].addPlayer(new Player(choose, getPlayerGender(playerList, choose)),mode));
			}while(teams[i].getPlayers().size() < (isSingle ? 1 : 2) );
		}
		
		System.out.println(Arrays.toString(teams));
		
	}

	// 파일에서 선수 데이터를 읽어옴(모드에 따라 필요한 인원만)
    private static List<String[]> readPlayerDataFromFile(String filename, String gender) {
        List<String[]> players = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                // 선수 정보는 "이름,성별" 형식으로 저장되어 있다고 가정
                String[] data = line.split(",");
                if (gender.equals("MIX") || gender.equals(data[1]))
                	players.add(data);
            }
        } catch (IOException e) {
            System.out.println("파일 읽기 중 오류 발생: " + e.getMessage());
        }
        return players;
    }
    
    // 선수 유효성 검사 - 올바른 이름
    private static boolean checkPlayer(List<String[]> players, String name) {
    	Iterator <String[]> it = players.iterator();
    	while (it.hasNext()) {
			String[] infos = (String[]) it.next();
			if (infos[0].equals(name)) return true;
		}
    	System.out.println("존재하지 않는 선수");
		return false;
	}
    
    // 리스트로부터 선수 성별 가져오기
    private static char getPlayerGender(List<String[]> players, String name) {
    	Iterator <String[]> it = players.iterator();
    	while (it.hasNext()) {
			String[] infos = (String[]) it.next();
			if (infos[0].equals(name)) return infos[1].charAt(0);
		}
		return ' ';
	}
}

