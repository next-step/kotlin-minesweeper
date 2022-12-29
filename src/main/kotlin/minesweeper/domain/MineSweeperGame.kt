package minesweeper.domain

import minesweeper.domain.button.ButtonGraph
import minesweeper.domain.button.vendor.ButtonVendor

class MineSweeperGame(
    height: Int,
    width: Int,
    mineCount: Int
) {
    val buttonGraph: ButtonGraph

    private val buttonVendor: ButtonVendor

    init {
        require(height > 0 && width > 0) {
            "height and width must be greater than 0"
        }

        require(mineCount in (0..height * width)) {
            "Mine count should be between ${(0..height * width)}"
        }

        buttonVendor = ButtonVendor(height, width)

        buttonGraph = buttonVendor.getButtonGraph(mineCount = mineCount)
    }
}
