package minesweeper.domain.generator

import minesweeper.domain.BoardMeta
import minesweeper.domain.field.Position

fun interface PositionGenerator {
    fun get(boardMeta: BoardMeta): Set<Position>
}