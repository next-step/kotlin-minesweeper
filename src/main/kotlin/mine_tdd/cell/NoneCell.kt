package mine_tdd.cell

class NoneCell(
    position: Position,
    nearMineCount: Int = DEFAULT_MINE_COUNT,
) : Cell(position, nearMineCount) {
    companion object {
        private const val DEFAULT_MINE_COUNT = 0
    }
}
