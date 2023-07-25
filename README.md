# kotlin-minesweeper

## Step 1 - 지뢰 찾기(그리기)

### 기능 목록

- [ ] 지뢰찾기 게임판(Board)을 그린다.
- [ ] 게임판은 포지션과 마크를 갖는다.
    - [ ] 게임판은 2차원 칸을 갖는다.
    - [ ] 게임판은 지뢰 위치를 받아 지뢰를 배치한다.
- [ ] 위치 생성기는 최대 x, y 좌표를 받아 랜덤한 위치를 생성한다.
- [ ] 마크는 지뢰와 일반 칸이 존재한다
- [X] 포지션은 x, y 좌표를 갖는다.
    - [X] x, y 좌표는 0이상이다

### 기능 요구사항

- 지뢰 찾기를 변형한 프로그램을 구현한다.
- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.

### 프로그래밍 요구사항

객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.

- 한 메서드에 오직 한 단계의 들여쓰기만 한다.
- else 예약어를 쓰지 않는다.
- 모든 원시 값과 문자열을 포장한다.
- 한 줄에 점을 하나만 찍는다.
- 줄여 쓰지 않는다(축약 금지).
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 일급 컬렉션을 쓴다.
- getter/setter/프로퍼티를 쓰지 않는다.
