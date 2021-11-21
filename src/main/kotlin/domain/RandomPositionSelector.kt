package domain

class RandomPositionSelector(board: Board) : PositionSelector(board.height(), board.width()) {
    override fun selectMinePositions(mineNumber: MineNumber, excludedPosition: Position): List<Position> {
        val positions = allPositions().toMutableList()
        positions.remove(excludedPosition)
        return (1..mineNumber.number).map { positions.removeAt(positions.indices.random()) }
    }
}
