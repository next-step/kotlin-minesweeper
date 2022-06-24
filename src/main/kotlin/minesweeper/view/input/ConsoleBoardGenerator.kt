package minesweeper.view.input

import minesweeper.model.board.Board
import minesweeper.model.board.Board.Companion.maxMineCountInRandomBoard
import minesweeper.model.board.BoardGenerator
import minesweeper.model.cell.RandomMineLocator
import minesweeper.model.coordinate.Area
import minesweeper.model.coordinate.BoardArea
import minesweeper.view.input.parser.IntInputParser

object ConsoleBoardGenerator : BoardGenerator {

    private const val MESSAGE_FOR_INPUT_ROW_COUNT = "높이를 입력하세요."
    private const val MESSAGE_FOR_INPUT_COLUMN_COUNT = "너비를 입력하세요."
    private const val MESSAGE_FOR_INPUT_MINE_COUNT = "지뢰는 몇 개인가요?"

    private const val MIN_COUNT_OF_ROW = 3
    private const val MIN_COUNT_OF_COLUMN = 3
    private const val MIN_COUNT_OF_MINE = 1

    override fun createBoard(): Board {
        val area = inputArea()
        val mineCount = inputMineCount(area)
        return RandomBoard(area = area, mineCount = mineCount)
    }

    private fun inputArea(): Area {
        val rowCount = ConsoleReader.read(MESSAGE_FOR_INPUT_ROW_COUNT, IntInputParser(minValue = MIN_COUNT_OF_ROW))
        val columnCount =
            ConsoleReader.read(MESSAGE_FOR_INPUT_COLUMN_COUNT, IntInputParser(minValue = MIN_COUNT_OF_COLUMN))
        return BoardArea.of(rowCount, columnCount)
    }

    private fun inputMineCount(area: Area): Int {
        val maxMineCount = area.maxMineCountInRandomBoard
        val mineCountRange = MIN_COUNT_OF_MINE..maxMineCount
        return ConsoleReader.read(MESSAGE_FOR_INPUT_MINE_COUNT, IntInputParser(mineCountRange))
    }
}

fun RandomBoard(area: Area, mineCount: Int) = Board(
    area = area,
    mineLocator = RandomMineLocator(area, mineCount)
)
