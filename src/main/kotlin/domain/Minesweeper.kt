package domain

import kotlin.random.Random

class Minesweeper(
    val height: Int,
    val width: Int,
    val mines: List<Mine>
) {

    var mineMap: List<MutableList<Char>> = List(height) { MutableList(width) { BASIC_SHAPE } }

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
            if (coordinate !in minePositions) {
                minePositions.add(coordinate)
            }
        }
        return minePositions
    }

    companion object {
        private const val BASIC_SHAPE = 'C'
    }
}