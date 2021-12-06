package minesweeper.domain.block

import minesweeper.domain.area.Area

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

object BlockGenerator {

    fun generateBlocks(area: Area, mineCount: Int): List<Block> {
        val range = (1..area.getArea())
        val randomPositions = (1..mineCount).map { range.random() - 1 }.toList()
        var blocks: List<Block> =
            (1..area.getArea()).map { None(Position(area.getX(it - 1), area.getY(it - 1))) }.toList()

        randomPositions.forEach { randomPosition ->
            val position = Position(area.getX(randomPosition), area.getY(randomPosition))
            val mine = Mine(position)
            blocks = blocks.replace(mine) { it == blocks[randomPosition] }
        }
        return blocks
    }
}
