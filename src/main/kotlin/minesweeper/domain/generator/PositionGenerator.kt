package minesweeper.domain.generator

import minesweeper.domain.BoardSize
import minesweeper.domain.field.Position

fun interface PositionGenerator {
    fun get(boardSize: BoardSize, count: Int): Set<Position>
}
