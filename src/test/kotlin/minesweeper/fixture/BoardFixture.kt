package minesweeper.fixture

import minesweeper.domain.Board
import minesweeper.domain.Height
import minesweeper.domain.Mines
import minesweeper.domain.Width

fun boardFixture(
    width: Int,
    height: Int,
    mines: Mines,
): Board =
    Board(
        height = Height(height),
        width = Width(width),
        mines = mines,
    )
