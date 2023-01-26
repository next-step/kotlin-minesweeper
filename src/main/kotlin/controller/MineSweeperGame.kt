package controller

import model.BoardHeight
import model.BoardMaker
import model.BoardWidth
import model.Mine
import view.InputView
import view.OutputView

class MineSweeperGame {
    private val inputView = InputView()
    private val outputView = OutputView()

    fun run() {
        val boardHeight = BoardHeight(inputView.getHeight())
        val boardWidth = BoardWidth(inputView.getWidth())
        val mine = Mine(inputView.getMineNumber())
        val boardMaker = BoardMaker()
        val board = boardMaker.run(boardHeight, boardWidth, mine)
        outputView.showBoard(board, boardWidth.width)
    }
}
