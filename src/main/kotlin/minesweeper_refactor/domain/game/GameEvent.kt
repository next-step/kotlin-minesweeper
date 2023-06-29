package minesweeper_refactor.domain.game

import minesweeper_refactor.domain.board.MinesweeperBoard
import minesweeper_refactor.domain.coordinate.Coordinate

class GameEvent(
    val openCoordinateEvent: () -> Coordinate,
    val currentBoardEvent: (MinesweeperBoard) -> Unit,
)
