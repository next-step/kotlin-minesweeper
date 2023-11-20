# kotlin-minesweeper

## 기능 요구사항
지뢰 찾기를 변형한 프로그램을 구현한다.
- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.
- 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.
- 지뢰가 없는 인접한 칸이 모두 열리게 된다.

## 실행 결과
```
높이를 입력하세요.
10

너비를 입력하세요.
10

지뢰는 몇 개인가요?
10

지뢰찾기 게임 시작
open: 1, 1
0 1 C C C C C C C C
0 1 C C C C C C C C
0 1 C C C C C C C C
1 1 C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C
C C C C C C C C C C

open: 4, 1
Lose Game.
```

## 프로그래밍 요구사항
- 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.

객체지향 생활 체조 원칙
1. 한 메서드에 오직 한 단계의 들여쓰기만 한다.
2. else 예약어를 쓰지 않는다.
3. 모든 원시값과 문자열을 포장한다.
4. 한 줄에 점을 하나만 찍는다.
5. 줄여쓰지 않는다(축약 금지).
6. 모든 엔티티를 작게 유지한다.
7. 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
8. 일급 콜렉션을 쓴다.
9. 게터/세터/프로퍼티를 쓰지 않는다.

- 객체 지향 5원칙을 지키면서 프로그래밍한다.

객체지향 5원칙(SOLID)
- SRP (단일책임의 원칙: Single Responsibility Principle): 작성된 클래스는 하나의 기능만 가지며 클래스가 제공하는 모든 서비스는 그 하나의 책임(변화의 축: axis of change)을 수행하는 데 집중되어 있어야 한다
- OCP (개방폐쇄의 원칙: Open Close Principle): 소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 변경에는 닫혀있어야 한다.
- LSP (리스코브 치환의 원칙: The Liskov Substitution Principle): 서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다. 즉, 서브 타입은 언제나 기반 타입과 호환될 수 있어야 한다.
- ISP (인터페이스 분리의 원칙: Interface Segregation Principle): 한 클래스는 자신이 사용하지 않는 인터페이스는 구현하지 말아야 한다.
- DIP (의존성역전의 원칙: Dependency Inversion Principle): 구조적 디자인에서 발생하던 하위 레벨 모듈의 변경이 상위 레벨 모듈의 변경을 요구하는 위계관계를 끊는 의미의 역전 원칙이다.


## 요구사항 분석
- [x] 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- [x] 높이의 값은 1 이상이어야 한다.
- [x] 너비의 값은 1 이상이어야 한다.
- [x] 높이와 너비를 가진 지뢰판을 생성한다.
- [x] 지뢰는 x, y 좌표를 가진다.
- [x] 지뢰찾기 게임은 지뢰판과 지뢰의 개수를 가진다.
- [x] 지뢰의 x, y 좌표는 높이와 너비의 범위 내에 있어야 한다.
- [x] 지뢰는 랜덤으로 배치한다.
- [x] 좌표가 주어졌을때, 해당 좌표에 지뢰가 있는지 확인할 수 있다.
- [x] 주어진 지뢰판과 지뢰의 개수를 가지고 지뢰를 배치한다.
- [x] 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.
- [x] 지뢰가 없는 사각형은 0을 표시한다.
- [x] 입력값을 받아서 해당 좌표의 사각형을 열 수 있다.
- [x] 해당 좌표의 숫자의 값이 0이라면, 주변 8개의 사각형에 0 이외의 숫자가 나올때까지 열어준다.
- [x] 해당 좌표의 숫자의 값이 0이 아니라면, 해당 좌표의 숫자를 표시한다.
- [x] 이미 열려있는 사각형은 열 수 없다.
- [x] 지뢰를 열었다면, 지뢰판 결과를 출력하고 게임을 종료한다.