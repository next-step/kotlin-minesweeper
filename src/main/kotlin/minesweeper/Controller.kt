package minesweeper

import minesweeper.model.MapBuilder
import minesweeper.model.MineMap
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val mapHeight: Int = InputView.mapHeight()
    val mapWidth: Int = InputView.mapWidth()
    val countOfMines: Int = InputView.countOfMines()
    val map: MineMap = MapBuilder.generate(mapHeight, mapWidth, countOfMines)
    OutputView.mineMap(map)
}
