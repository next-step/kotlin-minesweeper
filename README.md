# kotlin-minesweeper

---
### `1단계 - 지뢰찾기(그리기)`
<br>

##### `기능 요구사항`
- 지뢰 찾기를 변형한 프로그램을 구현
  - 높이와 너비, 지뢰 개수를 입력받을 수 있다.
  - 지뢰는 눈에 잘 띄는 것으로 표기한다.
  - "지뢰는 가급적 랜덤"에 가깝게 배치한다.

<br>

##### `실행결과`
```text
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

##### `요구사항 정리`
  - [ ] 맵은 높이(height)와 너비(width)를 가지는 2차원 배열
  - [ ] 지뢰는 좌표(Position)를 가진다
  - [ ] 맵의 좌표 하나에 지뢰가 설치 될수 있다
  - [ ] 지뢰는 입력된 개수만큼 생성된다
  - [ ] 지뢰의 생성은 랜덤이다
 