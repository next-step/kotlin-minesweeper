# kotlin-minesweeper

## 1. 기능 요구사항

지뢰 찾기를 변형한 프로그램을 구현한다.

높이와 너비, 지뢰 개수를 입력받을 수 있다.
지뢰는 눈에 잘 띄는 것으로 표기한다.
지뢰는 가급적 랜덤에 가깝게 배치한다.

## 2. 실행 예시

```
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
```

## 3. 세부 기능 및 테스트 구현 목록
- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
  - [x] Test 1 : 높이와 너비, 지뢰 개수를 입력받아 MineMap 객체를 생성할 수 있다.
    - 지뢰 갯수가 높이와 너비의 곱보다 크지 못하도록 설정함
  - [ ] Test 2 : location을 입력받으면 주위에 몇 개의 mine이 있는지 표시할 수 있다.
    - MineMap작성한 뒤 mineMap을 입력받아 각 element에 cnt입력하는 객체 생성
    - DisplayView에서 cnt를 출력
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
  - MineMap 출력하는 기능 추가
- 지뢰는 가급적 랜덤에 가깝게 배치한다.
  - RandomGenerator를 통해 '지뢰의 위치'를 생성받고 지뢰의 위치 리스트 작성
