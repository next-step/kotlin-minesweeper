package minesweeper.domain

enum class GameMessage {
    Start, Win, Lose, CellAlreadyOpened, CellNotFound,
}

class MineSweeper(
    val requestBoardSize: () -> Area,
    val requestMineCount: () -> MineCount,
    val requestOpenCoordinate: () -> Coordinate,
    val drawMessage: (GameMessage) -> Unit,
    val drawBoard: (Board) -> Unit
) {

    fun run() {
        val boardSize = requestBoardSize()
        val mineCount = requestMineCount()

        val board = Board(boardSize, mineCount)

        drawMessage(GameMessage.Start)
        drawBoard(board)

        play(board)
    }

    private fun play(board: Board) {
        do {
            val result = board.open(requestOpenCoordinate())

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
                    drawMessage(GameMessage.Win)
                } else {
                    drawBoard(board)
                }
            }
            CellsOpenResult.Fail -> {
                drawFinalBoard(board)
                drawMessage(GameMessage.Lose)
            }
            CellsOpenResult.AlreadyOpened -> drawMessage(GameMessage.CellAlreadyOpened)
            CellsOpenResult.NotFound -> drawMessage(GameMessage.CellNotFound)
        }
    }

    private fun drawFinalBoard(board: Board) {
        board.openAllMine()
        drawBoard(board)
    }
}
