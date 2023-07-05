package next.step.minesweeper.domain.mine.generator

import next.step.minesweeper.domain.board.BoardArea
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.position.Position
import next.step.minesweeper.domain.position.Positions

object RandomMineGenerator : MineGenerator {

    override fun generate(area: BoardArea, count: MineCount): Positions =
        Positions(
            area.rangeMap({ it }) { x, y -> area.select { Position(x, y) } }
                .flatten()
                .shuffled()
                .take(count.count())
                .toSet(),
        )
}
