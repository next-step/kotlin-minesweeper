package minesweeper.view

import minesweeper.domain.Board
import minesweeper.domain.Cell
import minesweeper.domain.GameState

internal class OutputView {
    fun renderStartMessage() {
        println("\n지뢰찾기 게임 시작")
    }

    fun renderBoard(board: Board, gameState: GameState) {
        board.render(gameState)
    }

    private fun Board.render(gameState: GameState) = when (gameState) {
        GameState.WIN -> {
            println("WIN Game.")
        }
        GameState.LOSE -> {
            println("WIN Game.")
        }
        GameState.RUNNING -> {
            cells.toList()
                .groupBy({ it.first.y }, { it.second })
                .forEach { (_, cells) ->
                    println(cells.joinToString(" ") { it.display })
                }
        }
    }

    fun renderMessage(message: String) {
        println(message)
    }

    private val Cell.display: String
        get() {
            return this.aroundMineCount?.toString() ?: "C"
        }
}
