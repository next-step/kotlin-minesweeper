package game.minesweeper

import game.minesweeper.domain.GameBoard
import game.minesweeper.ui.Input
import game.minesweeper.ui.Output

fun main() {
    val height = Input.getHeight()
    val width = Input.getWidth()
    val minesNumber = Input.getMinesNumber()

    val gameBoard = GameBoard(height, width)

    Output.printStartGamePrompt()
    gameBoard.startGame(minesNumber)

    Output.printGameResult(gameBoard)
}
