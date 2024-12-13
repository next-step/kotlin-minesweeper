package minesweeper.fixture

import minesweeper.config.BoardSize
import minesweeper.domain.Board
import minesweeper.domain.Height
import minesweeper.domain.Mines
import minesweeper.domain.Width

fun boardFixture(
    height: Height,
    width: Width,
    mines: Mines,
): Board =
    Board(
        size = BoardSize(height, width),
        mines = mines,
    )
