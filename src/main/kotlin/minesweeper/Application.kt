package minesweeper

import minesweeper.domain.Map
import minesweeper.domain.Mine
import minesweeper.domain.Position
import minesweeper.io.Input
import minesweeper.io.Output

fun main() {
    val input = Input()
    val output = Output()

    val maxSize = Position(input.getHeight(), input.getWidth())
    val mineCount = input.getMineCount()

    val mines = createRandomMine(maxSize, mineCount)
    val map = Map(maxSize, mines)

    output.printMap(map)
}

fun createRandomMine(maxSize: Position, count: Int): List<Mine> {
    return createRandomPosition(maxSize).reduce { acc: List<Position>, list: List<Position> ->
        acc.plus(list)
    }
        .map { Mine(it) }
        .shuffled()
        .slice(0 until count)
}

private fun createRandomPosition(maxSize: Position) = (1..maxSize.height).map { height: Int ->
    createPositionByWidth(maxSize.width, height)
}

private fun createPositionByWidth(maxWidth: Int, height: Int) = (1..maxWidth).map { width: Int ->
    Position(height, width)
}