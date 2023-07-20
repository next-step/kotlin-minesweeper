package step4

import step4.domain.MinesweeperGame
import step4.domain.MinesweeperGame.Companion.createNewGame
import step4.view.inputCoordinate
import step4.view.inputHeight
import step4.view.inputMineCount
import step4.view.inputWidth
import step4.view.printCurrentStatus
import step4.view.printStartGame

fun main() {
    val minesweeperGame = createNewGame(inputHeight(), inputWidth())
        .installMines(inputMineCount())

    printStartGame()
    run(minesweeperGame)
}

private tailrec fun run(minesweeperGame: MinesweeperGame) {
    if (minesweeperGame.isFinished()) {
        return
    }
    minesweeperGame.open(inputCoordinate().toCoordinate())
    printCurrentStatus(minesweeperGame.currentInfo())
    run(minesweeperGame)
}
