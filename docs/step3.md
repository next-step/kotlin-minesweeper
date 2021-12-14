# 3단계 - 지뢰 찾기(게임 실행)

## 기능 요구사항

> 지뢰 찾기를 변형한 프로그램을 구현한다.

* 높이와 너비, 지뢰 개수를 입력받을 수 있다.
* 지뢰는 눈에 잘 띄는 것으로 표기한다.
* 지뢰는 가급적 랜덤에 가깝게 배치한다.
* 각 사각형에 표시될 숫자는 자신을 제외한 주변 8개 사각형에 포함된 지뢰의 개수다.
* **지뢰가 없는 인접한 칸이 모두 열리게 된다.**

## 클래스 설계

* [x] OpenState : 열린 상태인지 아닌지를 나타낸 상태를 가진다.
    * isOpen(): Boolean
    * open(): Opened 상태 반환
* [x] Opened : 열린 상태
    * isOpen(): Boolean = true
    * open(): Opened = 예외처리 -> 이미 열려있으므로
* [x] UnOpened : 안열린 상태
    * isOpen(): Boolean = false
    * open(): Opened = Opened() 반환
* [x] GameState : 게임 중인 상태인지 아닌지를 나타낸 상태를 가진다.
    * isFinish(): Boolean
* [x] Running : 게임중인 상태
    * isFinished(): Boolean = false
* [x] Finish : 게임 종료 상태
    * isFinished(): Boolean = true
    * [x] Win
    * [x] Lose
* [x] Board: 
    * [x] open -> 현재 블록을 계산해서 로직을 수행한다.
        * [x] 지뢰인경우 -> Lose로 게임 종료  
        * [x] 지뢰가 아닌경우 -> 오픈을 하고, 0이면 다음칸도 열고 숫자면 해당 칸만 연다