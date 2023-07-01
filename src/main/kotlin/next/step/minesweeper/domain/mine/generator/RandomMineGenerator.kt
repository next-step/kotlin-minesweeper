package next.step.minesweeper.domain.mine.generator

import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.MinePosition
import next.step.minesweeper.domain.mine.MinePositions

object RandomMineGenerator : MineGenerator {

    override fun generate(board: Board, count: MineCount): MinePositions = MinePositions(
        (0 until board.area()).shuffled().take(count.count)
            .map { MinePosition(it % board.width(), it / board.width()) }.toSet()
    )
}
