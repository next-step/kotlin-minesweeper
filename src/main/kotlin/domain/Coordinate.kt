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
            Land()
        }
    }
}
