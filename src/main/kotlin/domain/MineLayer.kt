package domain

class MineLayer(private val board: Board, private val selector: PositionSelector) {
    fun layMines(mineNumber: MineNumber, position: Position = Position()) =
        selector.selectMinePositions(mineNumber, position).forEach { layMine(it) }

    private fun layMine(position: Position) {
        board.layMine(position)
        selector.adjacentPositions(position).forEach { board.increaseMineNumber(it) }
    }
}
