package domain

import domain.enums.GameStatus

class GameResult(gameStatus: GameStatus = GameStatus.PLAYING) {

    var gameStatus: GameStatus = gameStatus
        private set

    fun isContinued(): Boolean {
        return gameStatus == GameStatus.PLAYING
    }

    fun checkGameStatus(point: Point, board: List<CellList>) {
        checkGameLose(point, board)
        checkGameWin(board)
    }

    private fun checkGameLose(point: Point, board: List<CellList>) {
        if (board[point.row].cells[point.col].cellInfo.isMine()) {
            gameStatus = GameStatus.LOSE
        }
    }

    private fun checkGameWin(board: List<CellList>) {
        if (board.all { it.cells.all { cell -> cell.cellInfo.isOpened || cell.cellInfo.isMine() } }) {
            gameStatus = GameStatus.WIN
        }
    }
}
