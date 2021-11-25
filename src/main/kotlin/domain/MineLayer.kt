package domain

class MineLayer(private val board: Board, private val selector: PositionSelector) {
    fun layMines(mineNumber: MineNumber, position: Position = Position()) =
        selector
            .selectMinePositions(mineNumber, position)
            .forEach { layMine(it) }

    private fun layMine(position: Position) {
        board
            .getCell(position)
            .layMine()
        selector
            .adjacentPositions(position)
            .map { board.getCell(it) }
            .forEach { it.increaseMineNumber() }
    }
}
