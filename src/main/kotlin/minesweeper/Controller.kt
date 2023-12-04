package minesweeper

import minesweeper.app.MineSweeperGame
import minesweeper.model.board.Board
import minesweeper.model.board.toBoardLimit
import minesweeper.view.InputView
import minesweeper.view.OutputView
import minesweeper.view.render.impl.ExploringDefaultClosedAreaRenderingStrategy

fun main() {
    val mapHeight: Int = InputView.mapHeight()
    val mapWidth: Int = InputView.mapWidth()
    val minesCount: Int = InputView.countOfMines()
    val board = Board(minesCount, (mapHeight to mapWidth).toBoardLimit())
    val outputView = OutputView(ExploringDefaultClosedAreaRenderingStrategy)
    val game = MineSweeperGame(InputView, outputView)
    game.start(board)
}
