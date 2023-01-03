# 지뢰 찾기

## 1단계

### 기능 요구 사항

- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 띄는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다.

### 작업 목록

- [x] 높이와 너비를 반영해 판을 생성할 수 있다.
- [x] 지뢰의 개수를 반영해 판을 생성할 수 있다.
- [x] 일반 블록 객체 만들기
- [x] 지뢰 블록 객체 만들기
- [x] 유저로부터 요구사항을 입력받기 위한 InputView 생성
- [x] 메인 함수와 게임 컨트롤러 생성
- [x] 유저로부터 요구사항을 받아 보드 생성
- [x] 유저에게 게임보드를 보여주기 위한 ResultView 생성
- [x] 생성된 보드를 유저에게 보여주기

### 피드백 목록

- [x] `abstract` -> `sealed` 로 변경하여 확장에 제한두기
- [x] `buildList` 로직을 더 깔끔하게 변경
- [x] 불필요한 `OrNull` 제거
- [x] `Custom Exception` 사용
- [x] 지뢰의 수를 측정하는 메소드와 변수 정리
- [ ] `View` 를 `Interface`로 두고 `Controller`에 테스트 추가
  - [x] `View`를 `Interface`로 변경
- [x] `View` 에서 처리해야할 로직들 이동
   - [x] `Blcok` 클래스의 `mark` 삭제 -> `View`로 이동
   - [x] 모델들의 `toString()` -> `View`로 이동


## 2단계
- [x] 주변의 지뢰 숫자를 보여주는 기능
  - [x] `Point` - 근처의 포인트 객체들 받아오기
  - [x] `SafeBlock` - 주변의 지뢰 수를 가지는 프로퍼티 생성
  - [x] `MineSweeperBoard` - 지뢰를 심은 뒤에 지뢰의 수를 반영하는 메소드 생성
  - [x] `ResultView` - SafeBlock 표기 방식 변경