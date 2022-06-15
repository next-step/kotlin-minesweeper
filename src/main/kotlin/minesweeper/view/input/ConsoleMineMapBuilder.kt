package minesweeper.view.input

import minesweeper.model.MineMapBuilder
import minesweeper.model.map.MineMap
import minesweeper.model.map.coordinate.MapArea
import minesweeper.view.input.parser.IntInputParser

object ConsoleMineMapBuilder : MineMapBuilder {

    private const val MESSAGE_FOR_INPUT_ROW_COUNT = "높이를 입력하세요."
    private const val MESSAGE_FOR_INPUT_COLUMN_COUNT = "너비를 입력하세요."
    private const val MESSAGE_FOR_INPUT_MINE_COUNT = "지뢰는 몇 개인가요?"

    private const val MIN_COUNT_OF_ROW = 3
    private const val MIN_COUNT_OF_COLUMN = 3
    private const val MIN_COUNT_OF_MINE = 1

    override fun createNewMap(): MineMap {

        val rowCount = ConsoleReader.read(MESSAGE_FOR_INPUT_ROW_COUNT, IntInputParser(minValue = MIN_COUNT_OF_ROW))
        val columnCount =
            ConsoleReader.read(MESSAGE_FOR_INPUT_COLUMN_COUNT, IntInputParser(minValue = MIN_COUNT_OF_COLUMN))

        val maxMineCount = rowCount * columnCount
        val mineCountRange = MIN_COUNT_OF_MINE..maxMineCount

        val mineCount = ConsoleReader.read(MESSAGE_FOR_INPUT_MINE_COUNT, IntInputParser(mineCountRange))
        return MineMap.randomMap(MapArea(columnCount, rowCount), mineCount)
    }
}
