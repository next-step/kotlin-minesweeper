# kotlin-minesweeper

## STEP1 (1단계 - 지뢰 찾기(그리기))
### 실습 환경 구축
[지뢰찾기 저장소](https://github.com/next-step/kotlin-minesweeper)를 기반으로 미션을 진행한다. [온라인 코드 리뷰 요청 1단계 문서](https://github.com/next-step/nextstep-docs/blob/master/codereview/review-step1.md)를 참고해 실습 환경을 구축한다.
1. 미션 시작 버튼을 눌러 미션을 시작한다.
2. 저장소에 자신의 GitHub 아이디로 된 브랜치가 생성되었는지 확인한다.
3. 저장소를 자신의 계정으로 Fork 한다.

+ 코드리뷰 요청 1단계 [[동영상]](https://www.youtube.com/watch?v=YkgBUt7zG5k) [[문서]](https://github.com/next-step/nextstep-docs/blob/master/codereview/review-step1.md)
+ 코드리뷰 요청 2단계 [[동영상]](https://www.youtube.com/watch?v=HnTdFJd0PtU) [[문서]](https://github.com/next-step/nextstep-docs/blob/master/codereview/review-step2.md)
+ 코드리뷰 요청 3단계 [[동영상]](https://www.youtube.com/watch?v=fzrT3eoecUw) [[문서]](https://github.com/next-step/nextstep-docs/blob/master/codereview/review-step3.md)

#### 기능 요구 사항
지뢰 찾기를 변형한 프로그램을 구현한다.
- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.

#### 기능 목록
- [x] 높이는 최소 2이상, 최대 1000이하 여야 한다.
- [x] 넓이는 최소 2이상, 최대 1000이하 여야 한다.
- [x] 지뢰의 수는 1개 이상이여야 한다.
- [x] 보드의 모든 셀이 지뢰여서는 안된다.

#### 프로그래밍 요구 사항
- 객체지향 생활 체조 원칙을 지키면서 프로그래밍한다.

#### 객체지향 생활 체조 원칙
- 한 메서드에 오직 한 단계의 들여쓰기만 한다.
- else 예약어를 쓰지 않는다.
- 모든 원시 값과 문자열을 포장한다.
- 한 줄에 점을 하나만 찍는다.
- 줄여 쓰지 않는다(축약 금지).
- 모든 엔티티를 작게 유지한다.
- 3개 이상의 인스턴스 변수를 가진 클래스를 쓰지 않는다.
- 일급 컬렉션을 쓴다.
- getter/setter/프로퍼티를 쓰지 않는다.


## STEP2 (2단계 - 지뢰 찾기(지뢰 개수))

#### 기능 요구 사항
지뢰 찾기를 변형한 프로그램을 구현한다.
- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.
- 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.

#### 기능 목록
- [x] 높이는 최소 2이상, 최대 1000이하 여야 한다.
- [x] 넓이는 최소 2이상, 최대 1000이하 여야 한다.
- [x] 지뢰의 수는 1개 이상이여야 한다.
- [x] 보드의 모든 셀이 지뢰여서는 안된다.

#### 프로그래밍 요구 사항
- 객체 지향 5원칙을 지키면서 프로그래밍한다. 
  - 객체지향 5원칙(SOLID)

    - SRP (단일책임의 원칙: Single Responsibility Principle): 작성된 클래스는 하나의 기능만 가지며 클래스가 제공하는 모든 서비스는 그 하나의 책임(변화의 축: axis of change)을 수행하는 데 집중되어 있어야 한다
    - OCP (개방폐쇄의 원칙: Open Close Principle): 소프트웨어의 구성요소(컴포넌트, 클래스, 모듈, 함수)는 확장에는 열려있고, 변경에는 닫혀있어야 한다.
    - LSP (리스코브 치환의 원칙: The Liskov Substitution Principle): 서브 타입은 언제나 기반 타입으로 교체할 수 있어야 한다. 즉, 서브 타입은 언제나 기반 타입과 호환될 수 있어야 한다.
    - ISP (인터페이스 분리의 원칙: Interface Segregation Principle): 한 클래스는 자신이 사용하지 않는 인터페이스는 구현하지 말아야 한다.
    - DIP (의존성역전의 원칙: Dependency Inversion Principle): 구조적 디자인에서 발생하던 하위 레벨 모듈의 변경이 상위 레벨 모듈의 변경을 요구하는 위계관계를 끊는 의미의 역전 원칙이다.
