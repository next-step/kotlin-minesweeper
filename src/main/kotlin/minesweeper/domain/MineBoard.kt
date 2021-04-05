package minesweeper.domain

import kotlin.random.Random

class MineBoard(
    val width: Int,
    val height: Int,
    mineNumber: Int
) {
    val board: Array<Row> = Array(height) { Row(width) }

    init {
        require(mineNumber < width * height) { "마인의 개수가 너무 많습니다." }
        deployMine(mineNumber)
    }

    private fun deployMine(mineNumber: Int) {
        var deployedMine = 0
        while (deployedMine < mineNumber) {
            val row = Random.nextInt(0, height - 1)
            val column = Random.nextInt(0, width - 1)

            if (deploy(row, column)) deployedMine++
        }
    }

    private fun deploy(row: Int, column: Int): Boolean {
        return board[row].deploy(column)
    }

    companion object {
        const val MINE = " * "
        const val NORMAL = " C "
    }
}
