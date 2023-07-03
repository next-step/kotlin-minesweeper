package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.generator.MineGenerator
import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.utils.retryOnFailure

data class Board(private val rows: BoardRows, val area: BoardArea) {

    fun play(
        selector: () -> Position,
        onEachStep: (List<List<BoardPoint>>) -> Unit,
        onWin: (List<List<BoardPoint>>) -> Unit,
        onLose: (List<List<BoardPoint>>) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        while (rows.canUncover()) {
            val position = retryOnFailure({ area.select(selector) }, onFailure)
            if (rows.uncover(position)) return onLose(points())
            onEachStep(points())
        }
        onWin(points())
    }

    fun points(): List<List<BoardPoint>> = rows.points()

    companion object {

        fun of(area: BoardArea, mineGenerator: MineGenerator, mineCount: MineCount): Board {
            area.checkMaxCount(mineCount.count())
            val rows = BoardRows(area.rangeMap({ BoardRow(it) }) { _, _ -> BoardPoint.mineFree() })
            mineGenerator.generate(area, mineCount).forEach { mine ->
                rows.plantMine(mine)
                rows.notifyMine(mine)
            }
            return Board(rows, area)
        }
    }
}
