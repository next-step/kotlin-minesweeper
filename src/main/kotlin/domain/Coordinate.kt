package domain

import domain.block.Block
import domain.block.Land
import domain.block.Mine

data class Coordinate(
    val y: Pos,
    val x: Pos
) {
    fun toBlock(mines: List<Coordinate>): Block {
        return if (mines.contains(this)) {
            Mine()
        } else {
            Land()
        }
    }

    constructor(y: Int, x: Int) : this(Pos.of(y), Pos.of(x))
}
