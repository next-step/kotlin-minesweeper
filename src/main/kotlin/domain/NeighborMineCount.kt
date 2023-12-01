package domain

class NeighborMineCount(val count: Int) {
    init {
        require(count >= 0) { "count 는 음수일 수 없습니다." }
    }

    fun calculateNeighborMineCount(point: Point, boardSettings: BoardSettings, board: List<CellList>): NeighborMineCount {
        return NeighborMineCount(point.getNeighborPoints()
            .filter { it.isValid(boardSettings) }
            .map { board[it.row].cells[it.col] }
            .count { it.cellInfo.isMine() })
    }
}
