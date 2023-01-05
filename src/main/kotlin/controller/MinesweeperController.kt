package controller

import domain.Board
import domain.BoardFactory
import domain.Rectangle
import domain.state.Finished
import domain.state.Running
import domain.state.State
import view.InputView
import view.OutputView

class MinesweeperController {
    private val inputView = InputView
    private val outputView = OutputView

    fun play() {
        val board = initBoard()
        outputView.printGameStart()

        nextRound(Running(board = board))
        println("game end")
    }

    private tailrec fun nextRound(state: State): State {
        if (state is Finished) {
            return state
        }
        val position = inputView.inputOpenPosition()
        val nextState = state.open(position)
        outputView.printBoard(nextState.board)
        return nextRound(nextState)
    }

    private fun initBoard(): Board {
        val height = inputView.inputHeight()
        val width = inputView.inputWidth()
        val mineCount = inputView.inputMineCount()

        val rectangle = Rectangle(width, height)
        return BoardFactory().generate(rectangle, mineCount)
    }
}

fun main() {
    MinesweeperController().play()
}
