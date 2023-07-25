package minesweeper.domain.generator

import minesweeper.domain.field.Position

fun interface PositionGenerator {
    fun get(maxWidth: Int, maxHeight: Int): Position
}
