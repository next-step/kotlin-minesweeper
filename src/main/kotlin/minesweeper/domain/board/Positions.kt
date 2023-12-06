package minesweeper.domain.board

import minesweeper.domain.cell.Position

data class Positions(
    val allPositions: Set<Position>,
    val minePositions: Set<Position>,
) {

    val adjacentMineCountByPosition: Map<Position, Int> by lazy {
        val adjacentMineCountByPosition = minePositions
            .flatMap { it.adjacentPositions }
            .groupBy { it }
            .mapValues { it.value.size }

        allPositions.associateWith { adjacentMineCountByPosition[it] ?: 0 }
    }

    fun isMine(position: Position): Boolean = position in minePositions
}
