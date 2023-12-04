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

### 문자열 덧셈 계산기
새로운 언어를 배워 써먹을 만큼 숙련도를 높이려면 많이 노력해야 한다. 코틀린을 처음 배웠는데 정확한 코틀린 문법이 기억나지 않는 경우 유용하게 써먹을 수 있다.

#### 기능 요구 사항
지뢰 찾기를 변형한 프로그램을 구현한다.
- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.

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
