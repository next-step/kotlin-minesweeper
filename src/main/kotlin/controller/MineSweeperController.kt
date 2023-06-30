package controller

import domain.MineSweeperMap
import view.input.HeightInputView
import view.input.MineCountInputView
import view.input.WidthInputView
import view.output.GameMapOutputView
import view.output.GameStartOutputView

class MineSweeperController {
    fun start() {
        val height = HeightInputView().value
        val width = WidthInputView().value
        val mineCount = MineCountInputView().value

        val mineSweeperMap = MineSweeperMap(height, width, mineCount)

        GameStartOutputView()
        GameMapOutputView(mineSweeperMap)
    }
}
