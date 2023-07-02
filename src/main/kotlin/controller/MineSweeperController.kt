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
        val mineCount = MineCountInputView(height, width).value
        val mineMapProperty = MineSweeperMap.Property(height, width, mineCount)

        val mineSweeperMap = MineSweeperMap(mineMapProperty)

        GameStartOutputView()
        GameMapOutputView(mineSweeperMap)
    }
}
