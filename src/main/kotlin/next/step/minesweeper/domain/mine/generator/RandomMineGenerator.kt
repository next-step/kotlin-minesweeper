package next.step.minesweeper.domain.mine.generator

import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.MinePosition
import next.step.minesweeper.domain.mine.MinePositions

object RandomMineGenerator : MineGenerator {

    override fun generate(board: Board, count: MineCount): MinePositions {
        require(count.count() <= board.area()) { "지뢰 개수는 보드 넓이 이하여야 합니다." }
        return MinePositions(
            (0 until board.area()).shuffled().take(count.count())
                .map { MinePosition(it % board.width(), it / board.width()) }.toSet()
        )
    }
}
