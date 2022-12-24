package domain.coord

import domain.LocationOfMines
import domain.block.Block
import domain.block.Land
import domain.block.Mine
import domain.pos.AbstractPos
import domain.pos.Pos

data class AbstractCoordinate(
    override val y: Pos,
    override val x: Pos
) : Coordinate {
    constructor(y: Int, x: Int) : this(AbstractPos.of(y), AbstractPos.of(x))

    fun toBlock(mines: LocationOfMines): Block {
        return if (mines.exist(this)) {
            Mine()
        } else {
            Land(mines.countByNearMines(this))
        }
    }

    fun isPossiblePlus(target: Coordinate): Boolean {
        return y.isPossiblePlus(target.y) && x.isPossiblePlus(target.x)
    }
}
