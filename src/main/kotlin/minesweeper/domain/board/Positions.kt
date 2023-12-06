package minesweeper.domain.board

import minesweeper.domain.cell.Position

data class Positions(
    val value: Set<Position>,
) {
    lateinit var minePositions: Set<Position>
        private set

    val adjacentMineCountByPosition: Map<Position, Int> by lazy {
        val adjacentMineCountByPosition = minePositions
            .flatMap { it.adjacentPositions }
            .groupBy { it }
            .mapValues { it.value.size }

        value.associateWith { adjacentMineCountByPosition[it] ?: 0 }
    }

    val isMinePicked: Boolean
        get() = ::minePositions.isInitialized

    fun pickMines(positions: Set<Position>) {
        check(isMinePicked.not()) { "이미 지뢰가 선정되었습니다" }
        require(value.containsAll(positions)) { "주어진 위치에 해당하지 않습니다" }
        minePositions = positions
    }

    fun isMine(position: Position): Boolean = position in minePositions
}
