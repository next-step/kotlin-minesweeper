package tdd.minesweeper.domain

import tdd.minesweeper.domain.dsl.board

class Game(private val countOfMines: Int, board: Board) {
    var board = board
        private set

    fun open(location: Location) {
        board = board.open(location)
    }

    fun state(): GameState =
        when {
            board.countOfMineOpened() > 0 -> GameState.LOSE
            countOfMines == board.countOfClosed() -> GameState.WIN
            else -> GameState.CONTINUE
        }

    companion object {
        fun from(
            height: Int,
            width: Int,
            countOfMines: Int,
        ): Game {
            val board =
                board {
                    height(height)
                    width(width)
                    countOfMines(countOfMines)
                }
            return Game(countOfMines, board)
        }
    }
}
