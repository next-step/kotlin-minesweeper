package minesweeper.domain.state

import minesweeper.domain.Board

data class Win(override val board: Board) : Finished(board)
