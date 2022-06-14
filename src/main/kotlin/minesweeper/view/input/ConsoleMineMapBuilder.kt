package minesweeper.view.input

import minesweeper.model.MineMapBuilder
import minesweeper.model.map.MineMap
import minesweeper.model.map.coordinate.MapSize
import minesweeper.view.input.parser.IntInputParser

object ConsoleMineMapBuilder : MineMapBuilder {

    private const val MESSAGE_FOR_INPUT_HEIGHT = "높이를 입력하세요."
    private const val MESSAGE_FOR_INPUT_WIDTH = "너비를 입력하세요."
    private const val MESSAGE_FOR_INPUT_MINE_COUNT = "지뢰는 몇 개인가요?"

    private const val MIN_WIDTH_OF_MAP = 3
    private const val MIN_HEIGHT_OF_MAP = 3
    private const val MIN_MINE_COUNT = 1

    override fun createNewMap(): MineMap {

        val height = ConsoleReader.read(MESSAGE_FOR_INPUT_HEIGHT, IntInputParser(minValue = MIN_HEIGHT_OF_MAP))
        val with = ConsoleReader.read(MESSAGE_FOR_INPUT_WIDTH, IntInputParser(minValue = MIN_WIDTH_OF_MAP))

        val maxMineCount = height * with
        val mineCountRange = MIN_MINE_COUNT..maxMineCount

        val mineCount = ConsoleReader.read(MESSAGE_FOR_INPUT_MINE_COUNT, IntInputParser(mineCountRange))
        return MineMap.randomMap(MapSize(with, height), mineCount)
    }
}
