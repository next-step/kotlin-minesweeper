package minsweeper.domain.board

import minsweeper.domain.Cell
import minsweeper.domain.Coordinate
import minsweeper.domain.result.OpenResult

class Board(
    private val boardSize: BoardSize,
    val coordinatedCells: Map<Coordinate, Cell>,
) {

    fun open(coordinate: Coordinate): OpenResult {
        val cell = coordinatedCells[coordinate] ?: return OpenResult.INVALID_COORDINATE
        if (cell.isMine()) {
            return OpenResult.MINE_FOUND
        }

        cell.open()
        aroundCellOpen(coordinate)

        return OpenResult.SUCCESS
    }

    private fun aroundCellOpen(coordinate: Coordinate) {
        val island = coordinatedCells[coordinate] as? Cell.Island ?: return

        if (!island.isAroundMineAmountZero) {
            return
        }

        coordinate.aroundCoordinates(boardSize)
            .filter { it.isNotOpened() }
            .forEach { aroundCoordinate ->
                coordinatedCells[aroundCoordinate]?.open()
                aroundCellOpen(aroundCoordinate)
            }
    }

    private fun Coordinate.isNotOpened(): Boolean {
        return !(coordinatedCells[this]?.isOpened ?: false)
    }

}