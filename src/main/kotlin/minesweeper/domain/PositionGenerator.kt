package minesweeper.domain

class PositionGenerator(
    private val mineMapMeta: MineMapMeta,
    private val positionSelector: PositionSelector = RandomPositionSelector
) {
    private val allPositions = generateAllPositions()

    private fun generateAllPositions(): Set<Position> {
        return (Position.START_INDEX..mineMapMeta.height)
            .flatMap { y -> (Position.START_INDEX..mineMapMeta.width).map { x -> Position(y, x) } }
            .toSet()
    }

    fun generateMinePositions(): Set<Position> {
        val minePositions = positionSelector.select(allPositions, mineMapMeta.mineCount)
        require(minePositions.size == mineMapMeta.mineCount) { "지뢰의 개수가 맞지 않습니다." }
        return minePositions
    }

    fun generateEmptyPositions(
        minePositions: Set<Position>
    ): Set<Position> {
        val emptyPositions = allPositions - minePositions
        require(emptyPositions.intersect(minePositions).isEmpty()) { "지뢰와 빈 공간은 겹칠 수 없습니다." }
        return emptyPositions
    }
}
