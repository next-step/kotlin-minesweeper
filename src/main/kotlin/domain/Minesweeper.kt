package domain

import enums.MinesweeperShape
import kotlin.random.Random

class Minesweeper(
    val height: Int,
    val width: Int,
    val mines: List<Mine>
) {

    val mineMap: List<MutableList<MinesweeperShape>> = List(height) { MutableList(width) { MinesweeperShape.BASIC } }

    fun distributeMine(): Minesweeper {

        val minePositions = minePositions()

        minePositions.forEach { (row, column) ->
            mineMap[row][column] = MinesweeperShape.MINE
        }
        return this
    }

    fun changeMineCountMap(): Minesweeper {
        for (row in 0 until height) {
            processRow(row)
        }
        return this
    }

    private fun processRow(row: Int) {
        for (column in 0 until width) {
            updateMineShape(row, column)
        }
    }

    private fun updateMineShape(row: Int, column: Int) {
        if (mineMap[row][column] == MinesweeperShape.BASIC) {
            mineMap[row][column] = MinesweeperShape.values().find {
                it.printShape == getMineNumber(row, column).toString().first()
            } ?: MinesweeperShape.ZERO
        }
    }

    private fun getMineNumber(row: Int, column: Int): Int {
        var count = 0

        if (isExistMine(row - 1, column - 1)) count++
        if (isExistMine(row - 1, column)) count++
        if (isExistMine(row - 1, column + 1)) count++
        if (isExistMine(row, column - 1)) count++
        if (isExistMine(row, column + 1)) count++
        if (isExistMine(row + 1, column - 1)) count++
        if (isExistMine(row + 1, column)) count++
        if (isExistMine(row + 1, column + 1)) count++

        return count
    }

    private fun isExistMine(row: Int, column: Int): Boolean {
        return if (row < 0 || row >= height || column < 0 || column >= width) {
            false
        } else mineMap[row][column] == MinesweeperShape.MINE
    }

    private fun minePositions(): MutableList<Pair<Int, Int>> {
        val minePositions = mutableListOf<Pair<Int, Int>>()

        while (minePositions.size < mines.size) {
            val row = Random.nextInt(height)
            val column = Random.nextInt(width)
            val coordinate = row to column
            isCoordinateUnique(coordinate, minePositions)
        }
        return minePositions
    }

    private fun isCoordinateUnique(
        coordinate: Pair<Int, Int>,
        minePositions: MutableList<Pair<Int, Int>>
    ) {
        if (coordinate !in minePositions) {
            minePositions.add(coordinate)
        }
    }
}
