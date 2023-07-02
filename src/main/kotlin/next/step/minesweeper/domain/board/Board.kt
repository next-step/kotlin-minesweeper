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
        position.nearMinePositions().filter { it in area }.forEach { pointAt(it).notifyMine() }

    private fun pointAt(position: Position) = rows[position.y].pointAt(position.x)

    fun points(): List<List<BoardPoint>> = rows.map { it.points() }

    fun cover() = rows.forEach { it.cover() }
    
    companion object {

        fun mineFree(area: BoardArea): Board =
            Board(area.rangeMap({ BoardRow(it) }) { _, _ -> BoardPoint.mineFree() }, area)
    }
}
