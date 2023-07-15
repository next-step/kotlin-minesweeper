package domain

import kotlin.random.Random

class Minesweeper(
    val height: Int,
    val width: Int,
    val mines: List<Mine>
) {

    val mineMap: List<MutableList<Char>> = List(height) { MutableList(width) { BASIC_SHAPE } }

    fun distributeMine(): Minesweeper {

        val minePositions = minePositions()

        minePositions.forEach { (row, column) ->
            mineMap[row][column] = Mine.MINE_SHAPE
        }
        return this
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

    companion object {
        private const val BASIC_SHAPE = 'C'
    }
}
