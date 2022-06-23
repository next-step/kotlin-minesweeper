package minesweeper.domain.board

import minesweeper.domain.cell.Mine
import minesweeper.domain.cell.Position

class RandomMineMaker : MineMaker {

    override fun createMines(width: Int, height: Int, numberOfMines: Int): List<Mine> {
        val size = width * height
        val mineIndices = size.toShuffledMineIndices(numberOfMines)

        return mineIndices.map {
            val x = it % width
            val y = it / width
            val position = Position(it, x, y)
            Mine(position, position.getNearbyPositions(width, height))
        }
    }

    private fun Int.toShuffledMineIndices(numberOfMines: Int): List<Int> {
        return (0 until this).shuffled().subList(0, numberOfMines)
    }
}
