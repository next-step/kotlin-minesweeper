package minesweeper.domain

import minesweeper.enums.FindPosition

fun mineCount(minesweeperArray: Array<IntArray>, currentPosition: Pair<Int, Int>): Int {
    var mineCount = 0
    val (row, col) = currentPosition
    val findPositions = FindPosition.positions(row, col)

    findPositions
        .filter { isMineExists(minesweeperArray, it) }
        .forEach { _ -> mineCount += 1 }

    return mineCount
}

private fun isMineExists(minesweeperArray: Array<IntArray>, findMinePosition: Pair<Int, Int>): Boolean {
    val (row, col) = findMinePosition
    val rowSize = minesweeperArray.size
    val colSize = minesweeperArray[0].size

    if (row == -1 || col == -1 || row >= rowSize || col >= colSize) {
        return false
    }

    return minesweeperArray[row][col] == -1
}
