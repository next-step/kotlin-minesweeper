package domain.coord

import domain.pos.Pos

interface Coordinate {
    val y: Pos
    val x: Pos

    operator fun plus(target: RelativeCoordinate): AbstractCoordinate = AbstractCoordinate(y + target.y, x + target.x)
}
