package domain.coord

import domain.pos.Pos
import domain.pos.RelativePos

data class RelativeCoordinate(
    override val y: Pos,
    override val x: Pos
) : Coordinate {

    constructor(y: Int, x: Int) : this(RelativePos.of(y), RelativePos.of(x))

    override fun plus(target: Coordinate): Coordinate = RelativeCoordinate(y + target.y, x + target.x)
}
