package minesweeper.domain

interface OutputView {
    fun drawMessage(messageType: GameMessage)
    fun drawBoard(board: Board)
}
