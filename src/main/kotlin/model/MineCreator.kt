package model

import kotlin.random.Random

object MineCreator {
    tailrec fun createMine(
        rows: Int,
        cols: Int,
        mineCount: Int,
        mines: MutableSet<Location> = mutableSetOf()
    ): MutableSet<Location> =
        when (mines.size) {
            mineCount -> mines
            else -> {
                val randomRow = Random.nextInt(rows)
                val randomCol = Random.nextInt(cols)
                mines.add(Location(randomRow, randomCol))

                createMine(rows, cols, mineCount, mines)
            }
        }
}
