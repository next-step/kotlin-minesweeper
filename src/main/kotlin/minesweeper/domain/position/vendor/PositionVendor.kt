package minesweeper.domain.position.vendor

import minesweeper.domain.position.Positions
import minesweeper.domain.position.comma
import minesweeper.domain.position.indexRange

class PositionVendor(
    height: Int,
    width: Int
) {
    val size: Int = height * width

    private val positions: Positions = Positions(
        height.indexRange() comma width.indexRange()
    )

    init {
        require(height > 0 && width > 0) {
            "height and width must be greater than 0"
        }
    }

    fun getPositionsExcluding(excludingPositions: Positions): Positions =
        Positions(
            positions.shuffled().filterNot { it in excludingPositions }
        )

    fun getPositions(size: Int): Positions {
        require(size >= 0) {
            "size should be greater or equal to 0 [$size]"
        }

        return Positions(
            positions.shuffled().take(size)
        )
    }
}
