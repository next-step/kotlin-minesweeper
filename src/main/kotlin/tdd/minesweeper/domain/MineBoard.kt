package tdd.minesweeper.domain

import tdd.minesweeper.domain.type.AdjacentPoints
import tdd.minesweeper.domain.type.GameProgressStatus
import tdd.minesweeper.domain.type.SymbolType

class MineBoard(
    private val area: Area,
    private val rows: Rows,
) {
    private val remainCount: Count = Count(
        area.size - rows.findByFilter { it.equalsSymbol(SymbolType.MINE) }.count()
    )

    fun getRemainCount(): Int = remainCount.current

    fun marking(point: Point): GameProgressStatus {
        val foundPoint = rows.findByPoint(point)
        if (foundPoint.equalsSymbol(SymbolType.MINE)) {
            return GameProgressStatus.LOSE
        }

        if (foundPoint.mark()) {
            remainCount.decreaseAndGet()
            revealAdjacentPoint(foundPoint)
        }

        return if (remainCount.current == 0) GameProgressStatus.WIN
        else GameProgressStatus.CONTINUE
    }

    private fun revealAdjacentPoint(point: SymbolPoint) {
        if (!point.equalsSymbol(SymbolType.ZERO)) {
            return
        }

        AdjacentPoints.values().map { it.moving(point.point) }
            .filter { it in rows }
            .map(rows::findByPoint)
            .filter { it.isMarkable() }
            .forEach { marking(it.point) }
    }


}
