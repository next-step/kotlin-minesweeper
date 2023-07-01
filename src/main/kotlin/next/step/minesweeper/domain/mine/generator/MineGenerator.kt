package next.step.minesweeper.domain.mine.generator

import next.step.minesweeper.domain.board.Board
import next.step.minesweeper.domain.mine.MineCount
import next.step.minesweeper.domain.mine.MinePositions

interface MineGenerator {

    fun generate(board: Board, count: MineCount): MinePositions
}
