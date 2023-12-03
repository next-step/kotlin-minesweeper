package minesweeper.ui

import minesweeper.domain.MineSweeperWidth

object OutputView {
    fun enterHeight() {
        println("높이를 입력하세요.")
    }

    fun enterWidth() {
        println("너비를 입력하세요.")
    }

    fun enterMineCount() {
        println("지뢰는 몇개 인가요?")
    }

    fun startMineSweeper() {
        println("지뢰찾기 게임 시작")
    }

    fun mineSweeperInitializePrinter(mineSweeperInitializer: List<MineSweeperWidth>) {
        mineSweeperInitializer.forEach {
            println(it.joinToString())
        }
    }
}
