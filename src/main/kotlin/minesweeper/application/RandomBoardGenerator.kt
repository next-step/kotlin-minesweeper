package minesweeper.application

import minesweeper.domain.Board
import minesweeper.domain.Coordinate
import minesweeper.domain.EmptyCell
import minesweeper.domain.MinedCell

class RandomBoardGenerator : BoardGenerator {
    override fun generate(command: GenerateMinesweeperCommand): Board {
        val width = command.width
        val area = command.area
        val mineCount = command.mineCount

        val shuffledIndices = (0 until area).shuffled()

        val minedCells =
            shuffledIndices
                .take(mineCount)
                .associate { toCoordinate(it, width) to MinedCell() }

        val emptyCellCoordinates =
            shuffledIndices
                .drop(mineCount)
                .associate { toCoordinate(it, width) to EmptyCell() }

        return Board(minedCells + emptyCellCoordinates)
    }

    private fun toCoordinate(
        index: Int,
        width: Int,
    ): Coordinate = Coordinate(y = index / width, x = index % width)
}
