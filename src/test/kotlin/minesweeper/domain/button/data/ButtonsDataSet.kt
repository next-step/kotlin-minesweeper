package minesweeper.domain.button.data

import minesweeper.domain.button.Buttons

class ButtonsDataSet {
    companion object {
        fun testData(pushableButtonCount: Int, mineCount: Int): Buttons =
            Buttons(
                ButtonDataSet.testData(pushableButtonCount, mineCount)
            )
    }
}
