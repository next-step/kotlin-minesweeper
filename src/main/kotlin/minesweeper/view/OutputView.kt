package minesweeper.view

import minesweeper.domain.GameBoard
import minesweeper.domain.MinePin
import minesweeper.domain.NormalPin
import minesweeper.domain.Pin
import kotlin.reflect.KClass

object OutputView {
    val drawMap = mapOf<KClass<out Pin>, (Pin) -> Unit>(
        MinePin::class to { _ -> print("* ") },
        NormalPin::class to { pin -> print((pin as NormalPin).surroundMineNumber.toString() + " ") }
    )

    fun showMineSweeper(gameBoard: GameBoard) {
        val height = gameBoard.size.height - 1

        for (i in 0..height) {
            showMindSweeperInSameHeight(i, gameBoard)
        }
    }

    private fun showMindSweeperInSameHeight(height: Int, gameBoard: GameBoard) {
        val width = gameBoard.size.width - 1
        for (j in 0..width) {
            drawPin(gameBoard.getPin(height, j))
        }
        println()
    }

    private fun drawPin(pin: Pin) {
        drawMap[pin::class]!!.invoke(pin)
    }
}
