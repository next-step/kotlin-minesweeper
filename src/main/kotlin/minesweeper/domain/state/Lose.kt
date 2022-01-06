package minesweeper.domain.state

import minesweeper.domain.Board

data class Lose(override val board: Board) : Finished(board)
