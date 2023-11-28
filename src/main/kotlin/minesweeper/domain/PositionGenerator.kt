package minesweeper.domain

class PositionGenerator(
    private val mineMapMeta: MineMapMeta,
    private val positionSelector: PositionSelector = RandomPositionSelector
) {
    tailrec fun generateMinePositions(
        minePositions: Positions = Positions()
    ): Positions {
        if (minePositions.size == mineMapMeta.mineCount) { return minePositions }
        val randomMinePosition = positionSelector.select(mineMapMeta)
        return if (randomMinePosition in minePositions) {
            generateMinePositions(minePositions)
        } else {
            generateMinePositions(minePositions + randomMinePosition)
        }
    }

    fun generateEmptyPositions(
        minePositions: Positions
    ): Positions {
        val allPositions = generateAllPositions()
        require(allPositions.size == mineMapMeta.getCellCount()) { "모든 위치를 생성하지 못했습니다" }
        val emptyPositions = allPositions - minePositions
        require(!emptyPositions.containSamePosition(minePositions)) { "지뢰와 빈 공간은 겹칠 수 없습니다." }
        return emptyPositions
    }

    private fun generateAllPositions(): Positions {
        return (1..mineMapMeta.height)
            .flatMap { y -> (1..mineMapMeta.width).map { x -> Position(y, x) } }
            .toSet()
            .toPositions()
    }
}
