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
            blocks = blocks.replace(
                Mine(
                    Position(
                        area.getX(randomPosition),
                        area.getY(randomPosition)
                    )
                )
            ) { it == blocks[randomPosition] }
        }
        return blocks
    }
}
