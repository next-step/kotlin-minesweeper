package domain.coord

import domain.pos.Pos

interface Coord {
    val y: Pos
    val x: Pos

    operator fun plus(target: RelativeCoordinate): Coordinate = Coordinate(y + target.y, x + target.x)
}
