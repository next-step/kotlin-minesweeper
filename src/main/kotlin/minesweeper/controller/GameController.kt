package minesweeper.controller

import minesweeper.domain.Block
import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.MineSweeperException
import minesweeper.domain.Point
import minesweeper.view.InputView
import minesweeper.view.InputViewImpl
import minesweeper.view.ResultView
import minesweeper.view.ResultViewImpl

class GameController(
    private val inputView: InputView = InputViewImpl(),
    private val resultView: ResultView = ResultViewImpl()
) {
    fun run(): Map<Point, Block>? {
        return try {
            val gameBoard = setUp()
            resultView.renderInitialBoard(gameBoard.state)
            gameBoard.state
        } catch (e: MineSweeperException) {
            resultView.printKnownException(e)
            null
        } catch (e: Exception) {
            resultView.printUnknownException(e)
            null
        }
    }

    private fun setUp(): MineSweeperBoard {
        val height = inputView.readHeight()
        val width = inputView.readWidth()
        val mineCount = inputView.readMineCount()

        return MineSweeperBoard(height, width, mineCount)
    }
}
