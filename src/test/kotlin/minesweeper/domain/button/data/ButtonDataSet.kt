package minesweeper.domain.button.data

import minesweeper.domain.button.Button
import minesweeper.domain.button.Mine
import minesweeper.domain.button.PushableButton
import minesweeper.domain.position.Position

class ButtonDataSet {
    companion object {
        fun testDataList(pushableButtonPositions: List<Position>, minePositions: List<Position>): List<Button> {
            require(pushableButtonPositions.intersect(minePositions.toSet()).isEmpty()) {
                "Pushable button positions should not intersect mine positions"
            }

            val a = pushableButtonPositions.map { testDataList<PushableButton>(it) }
            val b = minePositions.map { testDataList<Mine>(it) }
            return a + b
        }

        private inline fun <reified T : Button> testDataList(position: Position): Button =
            when (T::class) {
                Mine::class -> Mine(position)
                PushableButton::class -> PushableButton(position)
                else -> error("Unsupported button type: ${T::class.qualifiedName}")
            }
    }
}
