package minesweeper

import minesweeper.domain.CityMap
import minesweeper.view.InputView
import minesweeper.view.ResultView

fun main() {
    val height = InputView.readHeight()
    val width = InputView.readWidth()
    val mineCount = InputView.readCountOfMines(height, width)

    val cityMap = CityMap(height, width, mineCount)

    ResultView.showCityMap(cityMap)
}
