package domain.strategy

import domain.MineCount
import domain.Position

class RandomMinePositionsSelectStrategy : MinePositionsSelectStrategy {
    override fun getMinePositions(positions: List<Position>, mineCount: MineCount): List<Position> {
        require(positions.size >= mineCount.value) { "지뢰 수는 전체 위치 수 보다 작아야합니다." }
        return positions.shuffled().take(mineCount.value)
    }
}
