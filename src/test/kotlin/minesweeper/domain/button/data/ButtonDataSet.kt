package minesweeper.domain.button.data

import minesweeper.domain.button.Button
import minesweeper.domain.button.Mine
import minesweeper.domain.button.PushableButton
import minesweeper.domain.position.data.PositionDataSet

class ButtonDataSet {
    companion object {
        fun testData(pushableButtonCount: Int, mineCount: Int): List<Button> {
            require(pushableButtonCount >= 0)
            require(mineCount >= 0)

            return (1..pushableButtonCount).map { testDataForPushableButton() } +
                    (1..mineCount).map { testDataForMine() }
        }

        private fun testDataForPushableButton(): Button = PushableButton(PositionDataSet.testData())

        private fun testDataForMine(): Button = Mine(PositionDataSet.testData())
    }
}
