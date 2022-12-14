package domain

import java.lang.IllegalArgumentException

class Field(
    private val block: Map<Coordinate, Block>
) {

    fun block(y: Int, x: Int): Block {
        return block[Coordinate(y, x)] ?: throw IllegalArgumentException("($y, $x) 는 없는 블록이에요.")
    }

    companion object {
        fun create(blocks: List<Block>): Field {
            return Field(blocks.associateBy { it.coordinate })
        }
    }
}
