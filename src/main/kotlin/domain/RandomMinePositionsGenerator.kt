package domain

class RandomMinePositionsGenerator(private val boardSize: BoardSize, private val mines: Int) : MinePositionsGenerator {
    init {
        require(mines > 0) { "지뢰는 0보다 많아야 합니다." }
        require(mines < boardSize.width * boardSize.height) { "지뢰는 게임판의 칸 수보다 적어야 합니다." }
    }

    override fun generate(): List<Position> {
        return randomPositions()
    }

    private fun randomPositions() = (0 until boardSize.width * boardSize.height)
        .shuffled()
        .take(mines)
        .map { Position(it % boardSize.width, it / boardSize.width) }
}
