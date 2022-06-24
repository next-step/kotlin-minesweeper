package minesweeper.model.board

import minesweeper.model.board.Board.Companion.maxMineCountInRandomBoard
import minesweeper.model.cell.RandomCellBuilder
import minesweeper.model.coordinate.Area

class RandomBoardBuilder(private val area: Area, mineCount: Int) : BoardBuilder {

    private val mineCount: Int = mineCount.coerceIn(1, area.maxMineCountInRandomBoard)

    override fun createNewBoard() = Board(area, RandomCellBuilder(area, mineCount))
}
