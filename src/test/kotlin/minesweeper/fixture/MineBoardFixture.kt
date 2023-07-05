package minesweeper.fixture

import minesweeper.domain.Board
import minesweeper.domain.Row

fun board(
    vararg rows: Row,
) = Board(rows.toList())
