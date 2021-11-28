package minesweeper.domain

fun <T> List<T>.replace(newValue: T, block: (T) -> Boolean): List<T> {
    return map {
        if (block(it)) newValue else it
    }
}

object BlockGenerator {

    fun generateBlocks(area: Area, mineCount: Int): List<Block> {
        val range = (1..area.getArea())
        val randomPositions = (1..mineCount).map { range.random() }.toList()
        var blocks: List<Block> = (1..area.getArea()).map { None() }.toList()
        randomPositions.forEach { randomPosition ->
            blocks = blocks.replace(Mine()) { it == blocks[randomPosition] }
        }
        return blocks
    }
}
