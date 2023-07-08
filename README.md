# kotlin-minesweeper


### [ 프로그래밍 요구사항 ]
- 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.
- 객체지향 생활 체조 원칙
  - 한 메서드에 오직 한 단계의 들여쓰기만 한다.
  - else 예약어를 쓰지 않는다.
  - 모든 원시 값과 문자열을 포장한다.
  - 한 줄에 점을 하나만 찍는다.
  - 줄여 쓰지 않는다(축약 금지).
  - 모든 엔티티를 작게 유지한다.
  - 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
  - 일급 컬렉션을 쓴다.
  - getter/setter/프로퍼티를 쓰지 않는다.

### [ 기능 요구사항 ]
(step1)
- [x] 높이와 너비, 지뢰 개수를 입력받을 수 있다
- [x] 지뢰는 눈에 잘 띄는 것으로 표기한다
- [x] 지뢰는 가급적 랜덤에 가깝게 배치한다


(step2)
- [x] 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.


(step3)
- [ ] 지뢰가 없는 인접한 칸이 모두 열리게 된다.

### [ 기능 목록 ]
(minesweeper)
- [x] 높이와 너비가 가지는 영역에 지뢰를 랜덤하게 위치시킨다
- [x] 특정 Cell의 위치에서 주변 8개 사각형에 포함된 유효한 Cell의 좌표를 구한다
- [x] 지뢰가 아닌 곳은 주변 8개 Cell 중 지뢰의 개수로 표기한다
- [x] 지뢰는 '*' 으로 표기한다
- [x] 각 Cell은 open 여부를 구분할 수 있어야 한다
- [x] 각 Cell은 주변 8개 좌표에 지뢰가 없는지 여부를 반환할 수 있어야 한다
- 특정 Cell을 open 했을 때 지뢰가 아닌 경우
  - [x] 주변 지뢰 개수가 0개이면 지뢰가 없는 인접한 칸을 모두 open
  - [x] 주변 지뢰 개수가 0보다 크다면 해당 칸만 open
- 게임 결과
  - [x] 지뢰가 아닌 칸이 모두 열리면, 게임이 승리한다
  - [x] 특정 좌표를 open 했을 때, 지뢰라면, 게임이 패배한다

(view)
- [x] 높이 입력을 받기 위한 메시지를 출력한다
- [x] 높이를 입력받는다


- [x] 너비 입력을 받기 위한 메시지를 출력한다
- [x] 너비를 입력받는다


- [x] 지뢰 개수를 입력받기 위한 메시지를 출력한다
- [x] 지뢰 개수를 입력받는다


- [x] 게임 시작 메시지를 출력한다
- [x] 지뢰찾기 게임의 초기 상태를 출력한다


- [x] 방문할 좌표를 입력받는 메시지를 출력한다
- [x] 방문할 좌표를 입력받는다


- [x] 현재 지뢰찾기 지도의 상태를 출력한다