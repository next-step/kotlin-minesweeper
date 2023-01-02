package domain.coord

import domain.pos.Pos

sealed interface Coordinate {
    val y: Pos
    val x: Pos

    operator fun plus(target: Coordinate): Coordinate
}
