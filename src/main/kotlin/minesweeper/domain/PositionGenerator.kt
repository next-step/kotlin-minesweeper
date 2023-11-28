package minesweeper.domain

class PositionGenerator(
    private val mineMapMeta: MineMapMeta,
    private val positionSelector: PositionSelector = RandomPositionSelector
) {
    tailrec fun generateMinePositions(
        minePositions: Set<Position> = emptySet()
    ): Set<Position> {
        // Position의 range는 항상 보장되어야 한다. 일급 객체 생성해서 받으면 될 것 같다.
        if (minePositions.size == mineMapMeta.mineCount) { return minePositions }
        val position = positionSelector.select(mineMapMeta)
        return if (position in minePositions) {
            generateMinePositions(minePositions)
        } else {
            generateMinePositions(minePositions + position)
        }
    }

    fun generateEmptyPositions(
        minePositions: Set<Position>
    ): Set<Position> {
        val allPositions = (1..mineMapMeta.height)
            .flatMap { y -> (1..mineMapMeta.width).map { x -> Position(y, x) } }
            .toSet()
        require(allPositions.size == mineMapMeta.getCellCount()) { "모든 위치를 생성하지 못했습니다" }
        require(allPositions.size > minePositions.size) { "지뢰 개수가 모든 위치 개수보다 많습니다" }
        return allPositions - minePositions
    }
}
