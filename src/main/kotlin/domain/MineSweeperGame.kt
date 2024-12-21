package domain

class MineSweeperGame(private val mineBoard: MineBoard, var result: GameResult = GameResult.FAILURE) {
    fun isContinueGame(): Boolean {
        when {
            mineBoard.isAnyMineCellOpened() -> return false
            mineBoard.isAllEmptyCellsOpened() -> {
                result = GameResult.SUCCESS
                return false
            }
        }
        return true
    }
}
