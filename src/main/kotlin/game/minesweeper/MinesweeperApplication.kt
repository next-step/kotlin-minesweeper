package game.minesweeper

import game.minesweeper.ui.Input

/*
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
*/
fun main() {
    val height = Input.getHeight()
    val width = Input.getWidth()
    val minesNumber = Input.getMinesNumber()
}
