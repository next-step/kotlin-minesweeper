package minesweeper.view

import minesweeper.domain.dto.MineSweeperDTO

object ResultView {
    fun boardView(mineSweeperDTO: MineSweeperDTO) {
        mineSweeperDTO.rows.forEach {
            println("$it")
        }
    }
}
