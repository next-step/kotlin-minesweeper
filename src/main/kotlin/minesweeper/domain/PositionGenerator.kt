package minesweeper.domain

class PositionGenerator(
    private val mineMapMeta: MineMapMeta,
    private val positionSelector: PositionSelector = RandomPositionSelector
) {
    private val allPositions = generateAllPositions()

    private fun generateAllPositions(): Positions {
        val allPositions = (Position.START_INDEX..mineMapMeta.height)
            .flatMap { y -> (Position.START_INDEX..mineMapMeta.width).map { x -> Position(y, x) } }
            .toSet()
            .toPositions()
        require(allPositions.size == mineMapMeta.getCellCount()) { "모든 위치를 생성하지 못했습니다" }
        return allPositions
    }

    fun generateMinePositions(): Positions {
        val minePositions = positionSelector.select(allPositions, mineMapMeta.mineCount)
        require(minePositions.size == mineMapMeta.mineCount) { "지뢰의 개수가 맞지 않습니다." }
        return minePositions
    }

    fun generateEmptyPositions(
        minePositions: Positions
    ): Positions {
        val emptyPositions = allPositions - minePositions
        require(!emptyPositions.containSamePosition(minePositions)) { "지뢰와 빈 공간은 겹칠 수 없습니다." }
        return emptyPositions
    }
}
