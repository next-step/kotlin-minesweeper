package next.step.minesweeper.domain.board

import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.generator.MineGenerator
import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.utils.retryOnFailure

data class Board(
    private val area: BoardArea,
    private val points: BoardPoints,
) {

    fun play(
        selector: () -> Position,
        onEachStep: (List<List<BoardPoint>>) -> Unit,
        onWin: (List<List<BoardPoint>>) -> Unit,
        onLose: (List<List<BoardPoint>>) -> Unit,
        onFailure: (Throwable) -> Unit,
    ) {
        while (points.canUncover()) {
            val position = retryOnFailure({ area.select(selector) }, onFailure)
            if (points.uncover(position, area)) return onLose(points())
            onEachStep(points())
        }
        onWin(points())
    }

    fun points(): List<List<BoardPoint>> = points.groupByRow()

    companion object {

        fun of(area: BoardArea, mineGenerator: MineGenerator, mineCount: MineCount): Board {
            area.checkMaxCount(mineCount.count())
            return Board(area, BoardPoints.of(area, mineGenerator.generate(area, mineCount)))
        }
    }
}
