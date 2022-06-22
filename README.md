# kotlin-minesweeper

## Step 1 - 지뢰 찾기(그리기)

### 기능 요구사항

지뢰 찾기를 변형한 프로그램을 구현한다.

- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.
  <br />
  <br />

### 프로그래밍 요구 사항

- 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다. 

객체지향 생활 체조 원칙

1. 한 메서드에 오직 한 단계의 들여쓰기만 한다.
2. else 예약어를 쓰지 않는다.
3. 모든 원시 값과 문자열을 포장한다.
4. 한 줄에 점을 하나만 찍는다.
5. 줄여 쓰지 않는다(축약 금지).
6. 모든 엔티티를 작게 유지한다.
7. 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
8. 일급 컬렉션을 쓴다.
9. getter/setter/프로퍼티를 쓰지 않는다.
  <br />
  <br />

### 구현 목록

- [ ] 사용자로부터 높이를 받는다.
    - [ ] 높이 입력 안내문을 출력한다.
    - [ ] 높이를 입력 받는다.
- [ ] 사용자로부터 너비를 받는다.
    - [ ] 너비 입력 안내문을 출력한다.
    - [ ] 너비를 입력 받는다.
- [ ] 지뢰 개수를 입력 받는다.
    - [ ] 지뢰 개수 입력 안내문을 출력한다.
    - [ ] 지뢰 개수를 입력 받는다.
- [ ] 지뢰 찾기 게임 보드판을 그린다.
    - [ ] 사용자가 입력한 높이, 너비, 지뢰 개수에 맞는 보드를 그린다.
    - [ ] 보드는 높이 만큼의 행(Row)로 이루어진다.
    - [ ] 각각의 행은 너비 만큼의 크기(열)의 셀(Cell)로 이루어진다.
    - [ ] 지뢰가 아닌 땅은 C, 지뢰는 *로 표시한다.
    - [ ] 지뢰는 랜덤으로 배치한다.
    - [ ] 만들어진 게임을 출력한다.

<br />

---