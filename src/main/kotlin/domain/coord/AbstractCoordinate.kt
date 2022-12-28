package domain.coord

import domain.pos.AbstractPos
import domain.pos.Pos

data class AbstractCoordinate(
    override val y: Pos,
    override val x: Pos
) : Coordinate {
    constructor(y: Int, x: Int) : this(AbstractPos.of(y), AbstractPos.of(x))

    fun isPossiblePlus(target: Coordinate): Boolean {
        return y.isPossiblePlus(target.y) && x.isPossiblePlus(target.x)
    }

    override fun plus(target: Coordinate): Coordinate = AbstractCoordinate(y + target.y, x + target.x)
}
