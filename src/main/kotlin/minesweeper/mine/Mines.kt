package minesweeper.mine

import minesweeper.position.Position

@JvmInline
value class Mines(
    val mines: Set<Position>
)
