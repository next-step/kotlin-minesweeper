package tdd.minesweeper.converter

import tdd.minesweeper.domain.Point

fun interface PointConverter {
    fun convert(point: Point): Point
}
