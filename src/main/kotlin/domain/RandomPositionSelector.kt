package domain

class RandomPositionSelector(board: Board) : PositionSelector(board.height(), board.width()) {
    override fun selectMinePositions(number: Int, excludedPosition: Position): List<Position> {
        val positions = allPositions().toMutableList()
        positions.remove(excludedPosition)
        return (1..number).map { positions.removeAt(positions.indices.random()) }
    }
}
