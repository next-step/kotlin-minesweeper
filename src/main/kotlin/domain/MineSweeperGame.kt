package domain

class MineSweeperGame(private val mineBoard: MineBoard, var result: GameResult = GameResult.FAILURE) {
    // todo: when 식으로 refactor
    fun isContinueGame(): Boolean {
        if (mineBoard.isAnyMineCellOpened()) {
            return false
        }

        if (mineBoard.isAllEmptyCellsOpened()) {
            result = GameResult.SUCCESS
            return false
        }
        return true
    }
}
