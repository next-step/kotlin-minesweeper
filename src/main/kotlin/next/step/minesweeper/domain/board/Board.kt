package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.generator.MineGenerator
import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.utils.retryOnFailure

data class Board(private val rows: List<BoardRow>, val area: BoardArea) {

    fun play(
        selector: () -> Position,
        onEachStep: (List<List<BoardPoint>>) -> Unit,
        onWin: (List<List<BoardPoint>>) -> Unit,
        onLose: (List<List<BoardPoint>>) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        while (canUncover()) {
            val position = retryOnFailure({ area.select(selector) }, onFailure)
            if (uncover(position)) return onLose(points())
            onEachStep(points())
        }
        onWin(points())
    }

    private fun canUncover(): Boolean = rows.any { it.canUncover() }

    private fun uncover(position: BoardPosition): Boolean {
        val point = pointAt(position)
        point.uncover()
        uncoverNear(point, position)
        return point.isUncoveredMine()
    }

    private fun pointAt(position: BoardPosition): BoardPoint = rows[position.y()].pointAt(position.x())

    private fun uncoverNear(point: BoardPoint, position: BoardPosition) {
        if (point.isUncoveredMineFree()) {
            position.near().forEach { uncoverNotMine(it) }
        }
    }

    private fun uncoverNotMine(position: BoardPosition) {
        val point = pointAt(position)
        if (point.canUncover()) {
            point.uncover()
            uncoverNear(point, position)
        }
    }

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }

    companion object {

        fun of(area: BoardArea, mineGenerator: MineGenerator, mineCount: MineCount): Board {
            area.checkMaxCount(mineCount.count())
            val rows = area.rangeMap({ BoardRow(it) }) { _, _ -> BoardPoint.mineFree() }
            mineGenerator.generate(area, mineCount).forEach { mine ->
                plantMine(rows, mine)
                notifyMine(rows, mine)
            }
            return Board(rows, area)
        }

        private fun plantMine(rows: List<BoardRow>, mine: BoardPosition) = rows[mine.y()].plantMine(mine.x())

        private fun notifyMine(
            rows: List<BoardRow>,
            mine: BoardPosition,
        ) {
            mine.near().forEach { rows[it.y()].pointAt(it.x()).notifyMine() }
        }
    }
}
