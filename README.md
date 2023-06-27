# kotlin-minesweeper


## 1단계 - 지뢰 찾기(그리기)

- 지뢰 찾기를 변형한 프로그램을 구현한다.

- 높이와 너비, 지뢰 개수를 입력 받기
- 지뢰는 `*` 표기
- 지뢰는 가급적 랜덤에 가깝게 배치

````
높이를 입력하세요.
10

너비를 입력하세요.
10

지뢰는 몇 개인가요?
10

지뢰찾기 게임 시작
C C C * C C C * C C
C C * C * C C C C C
C C C C C C C C C C
C C C C C C C C C C
* C C C C C C C C C
C C C C C C * C C C
C C * C C C * C C C
C C C C C C * C C *
C C C C C C C C C C
C C C C C C C C C C
````

### MineGame

- 컨트롤러와 뷰를 연결하는 컨트롤러 및 서비스 역할

### Line

- 높이 또는 너비에 대한 정보 관리

### MineBoard

- 가로 세로 정보 조회

### InputView

- 문구 및 입력값 조회

### OutputView

지뢰 찾기 게임 시작

