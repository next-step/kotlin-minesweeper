package minesweeper.domain.support

import minesweeper.domain.Position
import minesweeper.domain.PositionSelector

object FixedPositionSelector : PositionSelector {
    override fun select(positions: Set<Position>, selectNum: Int): Set<Position> {
        // 오름차순 정렬된 위치 정보 중 mineCount 만큼을 지뢰로 설정 (행 우선)
        return positions
            .sortedWith(compareBy({ it.y }, { it.x }))
            .take(selectNum)
            .toSet()
    }
}
