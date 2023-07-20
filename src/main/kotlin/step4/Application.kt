package step4

import step4.domain.MinesweeperGame.Companion.createNewGame
import step4.view.inputHeight
import step4.view.inputMineCount
import step4.view.inputWidth

fun main() {
    val minesweeperGame = createNewGame(inputHeight(), inputWidth())
        .installMines(inputMineCount())
}