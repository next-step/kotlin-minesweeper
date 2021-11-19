package domain

class MineLayer(private val board: Board, private val positionSelector: PositionSelector) {
    fun layMines(mineNumber: Int, position: Position) =
        positionSelector.selectMinePositions(mineNumber, position).forEach { layMine(it) }

    private fun layMine(position: Position) {
        board.layMine(position)
        positionSelector.adjacentPositions(position).forEach { board.increaseMineNumber(it) }
    }
}
