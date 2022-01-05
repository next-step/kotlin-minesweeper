package minesweeper.domain.state

import minesweeper.domain.Board

class Win(override val board: Board) : Finished(board)
