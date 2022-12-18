package domain

import domain.block.Block

class Cell(
    val pos: Pos,
    val block: Block
) {

    companion object {
        fun init(value: Int, block: Block): Cell {
            return Cell(Pos.of(value), block)
        }
    }
}
