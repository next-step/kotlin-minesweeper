package minesweeper.model

import kotlin.random.Random

class MineSweeperGame(
    private val rows: Int,
    private val cols: Int,
    private val mines: Int
) {
    val minefield = Array(rows) { Array(cols) { "C" } }

    init {
        plantingMines()
    }

    private fun plantingMines() {
        var mineCount = 0

        while (mineCount < mines) {
            val row = Random.nextInt(rows)
            val col = Random.nextInt(cols)

            mineCount = plantingResult(row, col, mineCount)
        }
    }

    private fun plantingResult(row: Int, col: Int, mineCount: Int): Int {
        if (isIsland(row, col)) {
            plantingMine(row, col)
            return mineCount + 1
        }
        return mineCount
    }

    private fun isIsland(row: Int, col: Int) = minefield[row][col] == "C"

    private fun plantingMine(row: Int, col: Int) {
        minefield[row][col] = "*"
    }
}
