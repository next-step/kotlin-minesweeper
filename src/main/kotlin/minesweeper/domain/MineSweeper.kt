package minesweeper.domain

enum class GameMessage {
    Start, Win, Lose, CellAlreadyOpened, CellNotFound,
}

class MineSweeper(
    private val inputView: InputView,
    private val outputView: OutputView
) {

    fun run() {
        val boardSize = inputView.receiveBoardSize()
        val mineCount = inputView.receiveMineCount()

        val board = Board(boardSize, mineCount)

        outputView.drawMessage(GameMessage.Start)
        outputView.drawBoard(board)

        play(board)
    }

    private fun play(board: Board) {
        do {
            val result = board.open(inputView.receiveOpenCoordinate())

            val isGameOver = result == CellsOpenResult.Fail
            val isCompleted = board.isCompleted()
            val canNextTurn = !isCompleted && !isGameOver

            drawTurn(board, result)
        } while (canNextTurn)
    }

    private fun drawTurn(board: Board, result: CellsOpenResult) {
        when (result) {
            CellsOpenResult.Success -> {
                if (board.isCompleted()) {
                    drawFinalBoard(board)
                    outputView.drawMessage(GameMessage.Win)
                } else {
                    outputView.drawBoard(board)
                }
            }
            CellsOpenResult.Fail -> {
                drawFinalBoard(board)
                outputView.drawMessage(GameMessage.Lose)
            }
            CellsOpenResult.AlreadyOpened -> outputView.drawMessage(GameMessage.CellAlreadyOpened)
            CellsOpenResult.NotFound -> outputView.drawMessage(GameMessage.CellNotFound)
        }
    }

    private fun drawFinalBoard(board: Board) {
        board.openAllMine()
        outputView.drawBoard(board)
    }
}
