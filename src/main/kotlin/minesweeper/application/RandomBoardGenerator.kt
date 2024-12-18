package minesweeper.application

import minesweeper.domain.Board
import minesweeper.domain.ClosedEmptyCell
import minesweeper.domain.Coordinate
import minesweeper.domain.PlayableBoard
import minesweeper.domain.UndetonatedMineCell

class RandomBoardGenerator : BoardGenerator {
    override fun generate(command: GenerateMinesweeperCommand): Board {
        val width = command.width
        val area = command.area
        val mineCount = command.mineCount

        val shuffledIndices = (0 until area).shuffled()

        val minedCells =
            shuffledIndices
                .take(mineCount)
                .associate { toCoordinate(it, width) to UndetonatedMineCell }

        val emptyCellCoordinates =
            shuffledIndices
                .drop(mineCount)
                .associate { toCoordinate(it, width) to ClosedEmptyCell }

        return PlayableBoard(minedCells + emptyCellCoordinates)
    }

    private fun toCoordinate(
        index: Int,
        width: Int,
    ): Coordinate = Coordinate(y = index / width, x = index % width)
}
