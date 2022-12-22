package minesweeper.view

import minesweeper.domain.Block

object ResultView {

    fun renderInitialBoard(state: List<List<Block>>) {
        println()
        println("지뢰찾기 게임 시작")
        renderBoard(state)
    }

    private fun renderBoard(state: List<List<Block>>) {
        state.forEach { rows ->
            println(rows.joinToString().replace(",", ""))
        }
    }
}
