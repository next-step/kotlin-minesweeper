# 지뢰찾기

# 1단계 - 지뢰찾기(그리기)

## 기능 요구사항
- 지뢰 찾기를 변형한 프로그램을 구현한다
- 높이와 너비, 지뢰 개수를 입력받을 수 있다.
- 지뢰는 눈에 잘 듸는 것으로 표기한다.
- 지뢰는 가급적 랜덤에 가깝게 배치한다
- 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.

## step1
### todo list
- [x] 높이와 너비, 지뢰개수 입력받기
- [x] 개수별 높이와 너비 사이에 랜덤값으로 위치 정하기
- [x] 맞게 출력

### todo list 2
- [x] 중복지뢰 처리 어떻게할지?
- [x] 변수명 정리
- [x] 테스트명 정리
- [x] 린트체크

## step2

### todo list
- [x] 노말타입에 표시되는 숫자 주기
- [x] 출력할때 숫자로 표시
- [x] 신규피쳐 테스트
- [x] 린트체크
- [x] 근처 지뢰개수가 알맞게 반영되는지 테스트


### todo list
- [x] 투두리스트 구체적으로 작성하기
- [x] MineController에 멤버변수 제거해보기
- [x] 누락된 리뷰 (변수명 보드 제거) 반영하기
- [x] 복잡한 조건문 따로 명명하여 이해하기 쉽게하기
- [x] sealed class에는 data클래스 적용
- [x] 마인 클래스는 nearMineCount가 의미가 없으니 object로 활용
- [x] BoardRow에 미사용 변수 제거
- [x] 테스트에는 로직을 노출하지 않기