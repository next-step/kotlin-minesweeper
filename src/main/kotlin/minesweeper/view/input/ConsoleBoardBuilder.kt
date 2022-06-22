package minesweeper.view.input

import minesweeper.model.BoardBuilder
import minesweeper.model.RandomBoardBuilder
import minesweeper.model.board.Board
import minesweeper.model.board.RandomBoard.Companion.maxMineCountInRandomBoard
import minesweeper.model.board.coordinate.Area
import minesweeper.model.board.coordinate.BoardArea
import minesweeper.view.input.parser.IntInputParser

object ConsoleBoardBuilder : BoardBuilder {

    private const val MESSAGE_FOR_INPUT_ROW_COUNT = "높이를 입력하세요."
    private const val MESSAGE_FOR_INPUT_COLUMN_COUNT = "너비를 입력하세요."
    private const val MESSAGE_FOR_INPUT_MINE_COUNT = "지뢰는 몇 개인가요?"

    private const val MIN_COUNT_OF_ROW = 3
    private const val MIN_COUNT_OF_COLUMN = 3
    private const val MIN_COUNT_OF_MINE = 1

    override fun createNewBoard(): Board {
        val boardArea = inputBoardArea()
        val mineCount = inputMineCount(boardArea)
        return RandomBoardBuilder(boardArea, mineCount).createNewBoard()
    }

    private fun inputBoardArea(): Area {
        val rowCount = ConsoleReader.read(MESSAGE_FOR_INPUT_ROW_COUNT, IntInputParser(minValue = MIN_COUNT_OF_ROW))
        val columnCount =
            ConsoleReader.read(MESSAGE_FOR_INPUT_COLUMN_COUNT, IntInputParser(minValue = MIN_COUNT_OF_COLUMN))
        return BoardArea.of(rowCount, columnCount)
    }

    private fun inputMineCount(area: Area): Int {
        val maxMineCount = area.maxMineCountInRandomBoard()
        val mineCountRange = MIN_COUNT_OF_MINE..maxMineCount
        return ConsoleReader.read(MESSAGE_FOR_INPUT_MINE_COUNT, IntInputParser(mineCountRange))
    }
}
