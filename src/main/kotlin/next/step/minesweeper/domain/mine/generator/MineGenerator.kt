package next.step.minesweeper.domain.mine.generator

import next.step.minesweeper.domain.board.BoardArea
import next.step.minesweeper.domain.board.BoardPositions
import next.step.minesweeper.domain.mine.MineCount

interface MineGenerator {

    fun generate(area: BoardArea, count: MineCount): BoardPositions
}
