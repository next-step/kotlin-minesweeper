package minesweeper.view

import minesweeper.dto.MineSweeperDTO

object ResultView {
    fun gameStartView() {
        println("지뢰찾기 게임 시작")
    }

    fun boardView(mineSweeperDTO: MineSweeperDTO) {
        mineSweeperDTO.rows.forEach {
            println(it)
        }
    }

    fun loseResultView() {
        println("Lose Game.")
    }

    fun winResultView() {
        println("Win Game.")
    }
}
