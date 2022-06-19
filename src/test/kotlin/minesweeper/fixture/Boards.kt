package minesweeper.fixture

import minesweeper.model.RandomBoardBuilder
import minesweeper.model.board.Board
import minesweeper.model.board.coordinate.BoardArea

fun Board.Companion.randomBoard(boardArea: BoardArea, mineCount: Int): Board = RandomBoardBuilder(boardArea, mineCount)
    .createNewBoard()
