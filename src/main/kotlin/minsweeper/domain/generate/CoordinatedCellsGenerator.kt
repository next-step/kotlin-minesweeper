package minsweeper.domain.generate

import minsweeper.domain.BoardSize
import minsweeper.domain.Cell
import minsweeper.domain.Coordinate

class CoordinatedCellsGenerator(
    private val mineGenerator: MineGenerator = RandomMineGenerator(),
    private val cellGenerator: CellGenerator = CellGenerator(),
) {

    fun generate(
        boardSize: BoardSize,
        mineAmount: Int,
    ): Map<Coordinate, Cell> {
        val mines: List<Coordinate> = mineGenerator.generate(boardSize, mineAmount)

        return List(boardSize.height) { row ->
            List(boardSize.width) { column ->
                val coordinate = Coordinate(row + 1, column + 1)
                val cell = cellGenerator.generate(coordinate, mines, boardSize)
                coordinate to cell
            }
        }.flatten()
            .toMap()
    }

}