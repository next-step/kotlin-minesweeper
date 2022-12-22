package domain

import domain.block.Block
import domain.block.Land
import domain.block.Mine

data class Coordinate(
    val y: Pos,
    val x: Pos
) {
    constructor(y: Int, x: Int) : this(Pos.of(y), Pos.of(x))

    fun toBlock(mines: LocationOfMines): Block {
        return if (mines.exist(this)) {
            Mine()
        } else {
            Land(mines.countByNearMines(this))
        }
    }

    fun isPossiblePlus(target: Coordinate): Boolean {
        if (y.isNotPossiblePlus(target.y)) {
            return false
        }

        if (x.isNotPossiblePlus(target.x)) {
            return false
        }

        return true
    }

    operator fun plus(target: Coordinate): Coordinate = Coordinate(y + target.y, x + target.x)
}
