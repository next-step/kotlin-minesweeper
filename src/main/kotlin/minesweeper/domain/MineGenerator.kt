package minesweeper.domain

object MineGenerator {
    fun generate(height: Height, width: Width, mineCount: Int): MineSweeperMap {
        val positions = (Height.MINIMUM_HEIGHT..height.value).map {
            createRow(it, width)
        }.flatten()

        require(positions.size >= mineCount) { ERROR_MESSAGE }
        val minePositions = positions.shuffled().take(mineCount)
        return MineSweeperMap(createMineSweeperIndexes(positions, minePositions))
    }

    private fun createMineSweeperIndexes(positions: List<Position>, minePositions: List<Position>): List<MineSweeperIndex> {
        return positions.map { position ->
            val isMine = minePositions.contains(position)
            createMineSweeperIndex(position, isMine)
        }
    }

    private fun createMineSweeperIndex(position: Position, isMine: Boolean): MineSweeperIndex {
        if (isMine) {
            return MineSweeperIndex(position, MineStatus.MINE)
        }
        return MineSweeperIndex(position, MineStatus.NOT_MINE)
    }

    private fun createRow(y: Int, width: Width): List<Position> {
        return (1..width.value).map { Position(it, y) }
    }

    private const val ERROR_MESSAGE = "지뢰의 개수는 지뢰판의 크기보다 작아야 합니다."
}
