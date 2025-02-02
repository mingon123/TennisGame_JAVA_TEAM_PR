# 🎾 테니스 점수 계산기

## 📌 프로젝트 개요 & 목표

본 프로젝트는 실시간으로 테니스 경기 점수를 계산하고, 시각적으로 경기 상태를 추적할 수 있는 프로그램입니다. 이 프로젝트는 테니스의 점수 시스템과 그에 따른 규칙을 명확히 구현하여, 사용자가 경기 진행 상황을 보다 쉽게 확인할 수 있도록 돕습니다. OOP(Object-Oriented Programming) 방식을 적용하여 각 기능을 모듈화하고, 협업을 통한 효율적인 개발을 목표로 했습니다.

<br>

## 🎯 주요 요구사항

- 테니스 점수 계산 로직(포인트, 게임, 세트, 매치) 구현

- 듀스 및 어드밴티지 시스템 적용

- 플레이어 및 팀 점수 관리

- 경기 진행 상태를 출력하는 기능 제공

<br>

## 🎮 UI 흐름
  1️⃣ **게임 시작 화면**  
  2️⃣ **경기 유형 선택 (단식/복식/혼합복식)**  
  3️⃣ **선수 선택**  
  4️⃣ **경기 진행 (점수 표시, 듀스, 어드밴티지, 서브 변경)**  
  5️⃣ **최종 승자 출력 후 종료**

### 순서도
![image](https://github.com/user-attachments/assets/cf0120e4-ef65-428f-87c8-28a70587cf31)

<br>

### 클래스 다이어그램
![image](https://github.com/user-attachments/assets/643d6318-692e-428e-a5f2-77117fa23a07)


<br>

## 🛠 객체지향 설계 & 팀 구조

프로젝트는 객체지향 설계(OOP) 를 기반으로 하여 각 기능을 클래스로 분리하였습니다. 각 클래스는 단일 책임 원칙(SRP) 을 따르며, 유지보수성과 확장성을 고려해 설계되었습니다.


## 📌 클래스 다이어그램 & 역할

- CourtManager : 코드 체인지 및 서브 위치 변경을 관리

- GamePlaying : 테니스 경기의 전체 진행을 관리하는 핵심 클래스

- Print : 경기의 시각적 표현을 담당

- Player : 개별 선수 정보를 저장하는 객체

- Team : 두 명(복식) 또는 한 명(단식)으로 이루어진 팀을 구성

- Input : 게임 설정을 위한 사용자 입력 처리(세트 수, 경기 방식 등)

- Score : 테니스 점수 시스템을 구현

<br>

## 🔍 코드 로직 및 설명

1️⃣ Player 클래스 (개별 선수 정보 저장)

- 선수의 이름, 성별, 점수 를 관리

- resetScore() 메서드를 통해 점수를 초기화

```
public class Player {
    private String name;
    private String gender;
    private int score;

    public Player(String name, String gender) {
        this.name = name;
        this.gender = gender;
        this.score = 0;
    }

    public void resetScore() {
        this.score = 0;
    }
}
```

<br>

2️⃣ Team 클래스 (팀 점수 및 상태 관리)

- 팀에 속한 플레이어 목록, 팀 점수, 어드밴티지 상태 를 관리

- getTeamScore() 메서드를 통해 팀의 총점 계산

```
public class Team {
    private List<Player> players;
    private int score;
    private boolean isAdvantage;

    public int getTeamScore() {
        return players.stream().mapToInt(Player::getScore).sum();
    }

    public void resetTeamScore() {
        players.forEach(Player::resetScore);
        this.isAdvantage = false;
    }
}
```

<br>

3️⃣ Score 클래스 (점수 시스템 구현)

- 테니스 점수 규칙(포인트, 듀스, 어드밴티지, 승패 판별) 적용

- pointToPlayer() 메서드를 통해 점수 추가 및 상태 업데이트

- updateGameState() 메서드로 듀스 및 어드밴티지 적용

```
public void pointToPlayer(Player player) {
    if (gameWon) return;
    
    player.setScore(player.getScore() + 1);
    updateGameState();
}

private void updateGameState() {
    if (score1 >= 4 || score2 >= 4) {
        if (Math.abs(score1 - score2) >= 2) {
            gameWon = true;
        }
    }
}
```

<br>


## 💻 기술 스택
- Java 17
- Eclipse IDE (개발 환경)
- GitHub (버전 관리)

<br>

## 🤝 협업 과정 학습

GitHub, Discord, Notion 을 활용하여 프로젝트를 진행하였습니다.

협업 도구 &emsp; |&emsp; 사용 목적

GitHub &emsp;&emsp; |&emsp; 코드 형상 관리, 브랜치 활용

Discord &emsp;&emsp;|&emsp; 실시간 회의 및 토론

Notion &emsp;&emsp; |&emsp; 프로젝트 문서화 및 역할 분배

<br>

**팀원 역할 :**

- 양희동(팀장) : 노션 및 프로젝트 전반 관리, CourtManager 클래스 작성

- 김민곤(팀원) : Git 전반 관리 및 Player, Team, Score 클래스 작성

- 박정현(팀원) : 프로젝트 전반 관리 및 GamePlaying, Input 클래스 작성

- 박예진(팀원) : Player, Print 클래스 작성

- 김하은(팀원) : Team 클래스 작성

<br>

## 📢 결론 및 느낀점

#### 첫 협업 과정에서 겪은 어려움

**코드 스타일**
- 팀원마다 코드 작성 스타일이 달라, 초기에는 가독성과 유지보수성이 떨어졌었다.
- 효율적인 로직을 구상하고 싶었으나 경험 부족으로 인해 결과물이 아쉬웠다.

**Git 사용**
- 협업 연습을 위해 깃허브를 사용했으나, 초기 설정의 어려움과 사용의 어려웠다.


**요구 사항 변경**
- 프로젝트 진행 중간에 요구 사항이 변경되었을 때, 이에 맞춰 코드 수정이 어려웠다. 이를 해결하기 위해 순서도와 클래스 다이어그램을 활용하여 구조를 미리 정리했으나, 다소 불완전했던 점이 아쉬웠다.

<br>

#### 배운점

**점수 시스템 구현의 복잡성**
-  테니스 경기의 점수 계산 로직을 구현하면서 점수 시스템에 대한 복잡성을 체감하고, 이를 구현하기 위해 어떤 식으로 코드를 작성해야하는 지 공부하였다.

**Git 협업 경험**
- Git을 통한 브랜치 전략과 충돌 해결을 경험하며, 협업 능력을 키울 수 있었다.

**OOP 설계의 중요성**
- 각 클래스가 독립적인 역할을 갖도록 설계함으로써, 유지보수성과 확장성이 향상되었다.

<br><br><br>



📎 발표 자료
(같은 주제라 다른 조 발표 자료도 추가)

우리조(1조) 발표 자료
[테니스 게임 1조.pptx](https://github.com/user-attachments/files/18615071/1.pptx)

(2조) 발표자료
[테니스 게임 2조.pptx](https://github.com/user-attachments/files/18614159/E.2.pptx)

(3조) 발표자료
[테니스 게임 3조.pptx](https://github.com/user-attachments/files/18631736/3._.pptx)

