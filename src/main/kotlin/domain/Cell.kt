package domain

import domain.block.Block
import domain.pos.AbstractPos

class Cell(
    val pos: AbstractPos,
    val block: Block
) {
    companion object {
        fun init(value: Int, block: Block): Cell {
            return Cell(AbstractPos.of(value), block)
        }
    }
}
