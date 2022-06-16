package minesweeper.view.input

import minesweeper.model.BoardBuilder
import minesweeper.model.board.Board
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

        val rowCount = ConsoleReader.read(MESSAGE_FOR_INPUT_ROW_COUNT, IntInputParser(minValue = MIN_COUNT_OF_ROW))
        val columnCount =
            ConsoleReader.read(MESSAGE_FOR_INPUT_COLUMN_COUNT, IntInputParser(minValue = MIN_COUNT_OF_COLUMN))

        val maxMineCount = rowCount * columnCount
        val mineCountRange = MIN_COUNT_OF_MINE..maxMineCount

        val mineCount = ConsoleReader.read(MESSAGE_FOR_INPUT_MINE_COUNT, IntInputParser(mineCountRange))
        return Board.randomBoard(BoardArea(rowCount, columnCount), mineCount)
    }
}
