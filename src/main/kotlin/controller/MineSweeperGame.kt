package controller

import model.BoardHeight
import model.BoardMaker
import model.BoardWidth
import model.Mine
import view.InputView

class MineSweeperGame {
    private val inputView = InputView()

    fun run() {
        val boardHeight = BoardHeight(inputView.getHeight())
        val boardWidth = BoardWidth(inputView.getWidth())
        val mine = Mine(inputView.getMineNumber())
        val boardMaker = BoardMaker()
        boardMaker.run(boardHeight, boardWidth, mine)
    }
}
