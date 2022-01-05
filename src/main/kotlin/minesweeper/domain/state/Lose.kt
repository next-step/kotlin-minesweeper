package minesweeper.domain.state

import minesweeper.domain.Board

class Lose(override val board: Board) : Finished(board)
