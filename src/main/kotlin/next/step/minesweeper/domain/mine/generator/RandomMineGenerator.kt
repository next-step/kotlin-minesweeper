package next.step.minesweeper.domain.mine.generator

import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.MinePoints
import next.step.minesweeper.domain.point.Point

object RandomMineGenerator : MineGenerator {

    override fun generate(board: Board, count: MineCount): MinePoints = MinePoints.of(
        (0 until board.area()).shuffled().take(count.count)
            .map { Point.of(it % board.width(), it / board.width()) }.toSet()
    )
}
