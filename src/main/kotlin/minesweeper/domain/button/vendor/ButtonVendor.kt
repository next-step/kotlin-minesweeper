package minesweeper.domain.button.vendor

import minesweeper.domain.button.Button
import minesweeper.domain.button.ButtonGraph
import minesweeper.domain.button.Buttons
import minesweeper.domain.button.Mine
import minesweeper.domain.button.PushableButton
import minesweeper.domain.position.Position
import minesweeper.domain.position.Positions
import minesweeper.domain.position.vendor.PositionVendor

class ButtonVendor private constructor(
    private val positionVendor: PositionVendor
) {
    constructor(height: Int, width: Int) : this(PositionVendor(height, width))

    fun getButtonGraph(mineCount: Int): ButtonGraph =
        ButtonGraph(
            getAllButtons(mineCount = mineCount)
        )

    fun getButtonGraph(minePositions: Positions): ButtonGraph =
        ButtonGraph(
            getAllButtons(minePositions = minePositions)
        )

    private fun getAllButtons(mineCount: Int): Buttons {
        val minePositions = positionVendor.getPositions(mineCount)

        return getAllButtons(minePositions = minePositions)
    }

    private fun getAllButtons(minePositions: Positions): Buttons {
        val pushableButtonPositions: Positions = positionVendor.getPositionsExcluding(minePositions)

        val buttons = getButtons<Mine>(minePositions) +
            getButtons<PushableButton>(pushableButtonPositions)

        return Buttons(buttons)
    }

    private inline fun <reified T : Button> getButtons(positions: Positions): Buttons =
        Buttons(
            positions.map { getButton<T>(it) }
        )

    private inline fun <reified T : Button> getButton(position: Position): Button {
        return when (T::class) {
            Mine::class -> Mine(position)
            PushableButton::class -> PushableButton(position)
            else -> throw IllegalArgumentException("Unsupported button type: ${T::class.simpleName}")
        }
    }
}
