package minesweeper

import kotlin.random.Random

class MineBoard(
    val width: Int,
    val height: Int,
    mineNumber: Int
) {
    val board: Array<Array<String>> = Array<Array<String>>(width) { Array<String>(height) { " C " } }

    init {
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

    private fun isDuplicateMine(row: Int, column: Int): Boolean {
        return board[row][column] == " * "
    }

    private fun deploy(row: Int, column: Int): Boolean {
        if (!isDuplicateMine(row, column)) {
            board[row][column] = " * "
            return true
        }
        return false
    }
}