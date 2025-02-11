package minesweeper

import minesweeper.controller.MinesweeperController
import minesweeper.domain.FieldInfo
import minesweeper.infrastructure.ConsoleMinesweeperInputAdapter
import minesweeper.infrastructure.RandomMinePositionSelector
import minesweeper.view.InputVIew
import minesweeper.view.OutputView

fun main() {
    val inputVIew = InputVIew()
    val outputView = OutputView()
    val consoleMinesweeperInputAdapter = ConsoleMinesweeperInputAdapter(inputVIew)

    val controller = MinesweeperController(consoleMinesweeperInputAdapter, outputView, RandomMinePositionSelector())

    val fieldInfo = FieldInfo(controller.getFieldHeight(), controller.getFieldWidth())
    val mineCount = controller.getMineCount()

    val field = controller.makeNewField(fieldInfo, mineCount)

    controller.playGame(field)
}
