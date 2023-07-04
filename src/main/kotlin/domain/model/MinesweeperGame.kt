package domain.model

class MinesweeperGame(val map: GameMap) {
    var status: GameStatus = Ready
        private set

    fun start() {
        status = Running
    }

    fun playerTurn(point: Point) {
        if (map.isMine(point)) {
            gameOver()
            return
        }
        map.openTile(point)

        if (map.isAllOpened()) {
            win()
        }
    }

    private fun win() {
        status = Win
    }

    private fun gameOver() {
        status = GameOver
    }
}
