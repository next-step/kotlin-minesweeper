package minesweeper

import minesweeper.model.map.MapBuilder
import minesweeper.model.map.MineMap
import minesweeper.view.InputView
import minesweeper.view.OutputView

fun main() {
    val mapHeight: Int = InputView.mapHeight()
    val mapWidth: Int = InputView.mapWidth()
    val countOfMines: Int = InputView.countOfMines()
    val map: MineMap = MapBuilder.build(mapHeight, mapWidth, countOfMines)
    OutputView.mineMap(map)
}
