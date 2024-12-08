package minesweeper.fixture

import minesweeper.domain.Board
import minesweeper.domain.FakeMineGenerator
import minesweeper.domain.Height
import minesweeper.domain.MineCount
import minesweeper.domain.Width
import minesweeper.domain.point.Mines

fun boardFixture(
    width: Int,
    height: Int,
    mines: List<Pair<Int, Int>>,
): Board =
    Board(
        Height(height),
        Width(width),
        Mines(Height(height), Width(width), MineCount(0), FakeMineGenerator(mines)),
    )
