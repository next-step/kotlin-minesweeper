package minesweeper

import minesweeper.domain.Map
import minesweeper.domain.Mine
import minesweeper.domain.Position
import minesweeper.io.Input
import minesweeper.io.Output
import kotlin.random.Random

fun main() {
    val input = Input()
    val output = Output()

    val maxSize = Position(input.getHeight(), input.getWidth())
    val mineCount = input.getMineCount()

    val mines = getRandomMineList(maxSize, mineCount)
    val map = Map(maxSize, mines)

    output.printMap(map)
}

fun getRandomMineList(maxSize: Position, count: Int): List<Mine> {
    val mineList = mutableListOf<Mine>()

    while (mineList.count() != count) {
        val mine = createRandomMine(maxSize)
        if (!mineList.contains(mine)) mineList.add(mine)
    }

    return mineList
}

fun createRandomMine(maxSize: Position): Mine {
    val width = Random.nextInt(1, maxSize.width + 1)
    val height = Random.nextInt(1, maxSize.height + 1)
    return Mine(Position(width, height))
}
