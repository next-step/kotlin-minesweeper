package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MinePosition
import next.step.minesweeper.domain.mine.MinePositions
import next.step.minesweeper.domain.position.Position

data class Board(private val rows: List<BoardRow>, val area: BoardArea) {

    fun plantMines(positions: MinePositions) {
        area.requireArea(positions.count())
        positions.forEach {
            plantMine(it)
            notifyMine(it)
        }
    }

    private fun plantMine(position: MinePosition) {
        area.requireContains(position.x, position.y)
        rows[position.y].plantMine(position.x)
    }

    private fun notifyMine(position: MinePosition) =
        area.nearForEach(position.x, position.y) { pointAt(it).notifyMine() }

    private fun pointAt(position: Position): BoardPoint = rows[position.y].pointAt(position.x)

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }

    fun cover() = rows.forEach { it.cover() }

    fun play(
        selector: () -> Position,
        selectError: (Throwable) -> Unit,
        announce: (List<List<BoardPoint>>) -> Unit,
        success: (List<List<BoardPoint>>) -> Unit,
        fail: (List<List<BoardPoint>>) -> Unit
    ) {
        while (canUncover()) {
            val position = area.select(selector, selectError)
            if (uncover(position)) return fail(points())
            announce(points())
        }
        success(points())
    }

    private fun canUncover(): Boolean = rows.any { it.canUncover() }

    private fun uncover(position: Position): Boolean {
        val point = pointAt(position)
        point.uncover()
        uncoverNear(point, position)
        return point.isUncoveredMine()
    }

    private fun uncoverNear(point: BoardPoint, position: Position) {
        if (point.isUncoveredMineFree())
            area.nearForEach(position.x, position.y) { uncoverNotMine(it) }
    }

    private fun uncoverNotMine(position: Position) {
        val point = pointAt(position)
        if (point.canUncover()) {
            point.uncover()
            uncoverNear(point, position)
        }
    }

    companion object {

        fun mineFree(area: BoardArea): Board =
            Board(area.rangeMap({ BoardRow(it) }) { _, _ -> BoardPoint.mineFree() }, area)
    }
}
