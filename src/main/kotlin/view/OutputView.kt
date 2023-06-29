package view

import Minesweeper

class OutputView {

    fun showInputHeight() {
        println("높이를 입력하세요.")
    }

    fun showInputWidth() {
        println("너비를 입력하세요.")
    }

    fun showInputMineCount() {
        println("지뢰는 몇 개인가요?")
    }

    fun showMinesweeper(minesweeper: Minesweeper) {
        println("지뢰찾기 게임 시작")
        println(minesweeper)
    }
}
