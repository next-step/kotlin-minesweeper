package minesweeper.controller

import minesweeper.domain.MineSweeperBoard
import minesweeper.domain.exception.MineSweeperException
import minesweeper.domain.plant_strategy.RemainderPlantStrategy
import minesweeper.view.InputView
import minesweeper.view.InputViewImpl
import minesweeper.view.ResultView
import minesweeper.view.ResultViewImpl

class GameController(
    private val inputView: InputView = InputViewImpl(),
    private val resultView: ResultView = ResultViewImpl()
) {
    fun run(): MineSweeperBoard? {
        return try {
            val gameBoard: MineSweeperBoard = setUp()
            resultView.renderInitialBoard(gameBoard.state)
            gameBoard
        } catch (e: MineSweeperException) {
            resultView.printKnownException(e)
            throw e
        } catch (e: Exception) {
            resultView.printUnknownException(e)
            throw e
        }
    }

    private fun setUp(): MineSweeperBoard {
        val height = inputView.readHeight()
        val width = inputView.readWidth()
        val mineCount = inputView.readMineCount()

        return MineSweeperBoard(height, width, mineCount, RemainderPlantStrategy())
    }
}
