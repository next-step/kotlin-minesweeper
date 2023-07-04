package next.step.minesweeper.domain.mine.generator

import next.step.minesweeper.domain.board.BoardArea
import next.step.minesweeper.domain.board.BoardPositions
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.position.Position

object RandomMineGenerator : MineGenerator {

    override fun generate(area: BoardArea, count: MineCount): BoardPositions =
        BoardPositions(
            area.rangeMap({ it }) { x, y -> area.select { Position(x, y) } }
                .flatten()
                .shuffled()
                .take(count.count())
                .toSet(),
        )
}
