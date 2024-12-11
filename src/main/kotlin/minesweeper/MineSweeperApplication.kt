package minesweeper

import minesweeper.controller.MinesweeperController
import minesweeper.domain.Field
import minesweeper.domain.FieldInfo
import minesweeper.infrastructure.ConsoleMinesweeperInputAdapter
import minesweeper.view.InputVIew
import minesweeper.view.OutputView

fun main() {
    val inputVIew = InputVIew()
    val outputView = OutputView()
    val consoleMinesweeperInputAdapter = ConsoleMinesweeperInputAdapter(inputVIew)

    val controller = MinesweeperController(consoleMinesweeperInputAdapter, outputView)

    val fieldInfo = FieldInfo(controller.getFieldHeight(), controller.getFieldWidth())
    val mineCount = controller.getMineCount()

    val field = Field.createField(fieldInfo, mineCount)

    controller.announceInitialField(field)
}
