package minesweeper.domain.button.data

import minesweeper.domain.button.ButtonGraph
import minesweeper.domain.position.data.PositionsDataSet
import minesweeper.util.indexRange

class ButtonGraphDataSet {
    companion object {
        fun testData(height: Int, width: Int): ButtonGraph {
            require(height > 0 && width > 0) {
                "height and width must be greater than 0 [height: $height, width: $width]"
            }

            val totalMineCount = (0..height * width).random()

            val minePositions =
                PositionsDataSet.testData(totalMineCount, height.indexRange(), width.indexRange())

            return ButtonGraph.of(height, width, totalMineCount, minePositions)
        }
    }
}
