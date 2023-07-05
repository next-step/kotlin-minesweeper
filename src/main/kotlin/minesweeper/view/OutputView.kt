package minesweeper.view

import minesweeper.domain.GameBoard
import minesweeper.domain.MinePin
import minesweeper.domain.NormalPin
import minesweeper.domain.Pin
import kotlin.reflect.KClass

object OutputView {
    private val drawMap = mapOf<KClass<out Pin>, (Pin) -> Unit>(
        MinePin::class to { _ -> print("* ") },
        NormalPin::class to { pin -> print((pin as NormalPin).surroundMineNumber.toString() + " ") }
    )

    fun showMineSweeper(gameBoard: GameBoard) {
        for (i in 0 until gameBoard.size.height) {
            showMindSweeperInSameHeight(i, gameBoard)
        }
    }

    private fun showMindSweeperInSameHeight(height: Int, gameBoard: GameBoard) {
        for (j in 0 until gameBoard.size.width) {
            drawPin(gameBoard.getPinAt(height, j))
        }
        println()
    }

    private fun drawPin(pin: Pin) {
        drawMap[pin::class]!!.invoke(pin)
    }
}
