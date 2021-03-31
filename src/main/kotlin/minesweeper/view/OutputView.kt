package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.GameState
import minesweeper.domain.NaturalNumber

internal class OutputView {
    fun renderStartMessage() {
        println("\n지뢰찾기 게임 시작")
    }

    fun renderBoard(board: Board, gameState: GameState) {
        board.render(gameState)
    }

    private fun Board.render(gameState: GameState) {
        if (gameState == GameState.WIN) {
            println("WIN Game.")
            return
        }

        if (gameState == GameState.LOSE) {
            println("LOSE Game.")
            return
        }
        var curY = NaturalNumber.ZERO
        this.cells.forEach {
            if (it.key.y != curY) {
                curY = it.key.y
                println()
            }

            print("${it.value.display} ")
        }
        println()
    }

    private val Cell.display: String
        get() {
            return this.aroundMineCount?.toString() ?: "C"
        }
}
