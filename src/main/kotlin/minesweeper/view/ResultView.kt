package minesweeper.view

import minesweeper.domain.dto.MineSweeperDTO

object ResultView {
    fun boardView(mineSweeperDTO: MineSweeperDTO) {
        println("지뢰찾기 게임 시작")
        mineSweeperDTO.rows.forEach {
            println(it)
        }
    }
}
