package model.board

import model.Position
import model.Positions

class BoardFactory {
    fun create(boardSize: BoardSize, minePositions: Positions): Board {
        return Board(
            (0 until boardSize.height).map { heightIndex ->
                buildRow(boardSize.width, heightIndex, minePositions)
            }
        )
    }

    private fun buildRow(width: Int, heightIndex: Int, minePositions: Positions): Row {
        return Row(
            (0 until width).map { widthIndex ->
                decideCell(Position.get(heightIndex, widthIndex), minePositions)
            }
        )
    }

    private fun decideCell(cellPosition: Position, minePositions: Positions) =
        if (cellPosition in minePositions) Cell.MINE_CELL else Cell.DEFAULT_CELL
}
