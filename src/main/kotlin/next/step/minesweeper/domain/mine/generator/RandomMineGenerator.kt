package next.step.minesweeper.domain.mine.generator

import next.step.minesweeper.domain.board.BoardArea
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.MinePosition
import next.step.minesweeper.domain.mine.MinePositions

object RandomMineGenerator : MineGenerator {

    override fun generate(area: BoardArea, count: MineCount): MinePositions {
        area.requireArea(count.count())
        return MinePositions(
            area.rangeMap({ it }) { x, y -> MinePosition(x, y) }.flatten().shuffled().take(count.count()).toSet(),
        )
    }
}
