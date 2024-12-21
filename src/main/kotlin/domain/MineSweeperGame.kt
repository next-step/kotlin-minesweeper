package domain

class MineSweeperGame(private val mineBoard: MineBoard) {
    var result: GameResult = GameResult.FAILURE

    fun isContinueGame(): Boolean {
        if (isAnyMineCellOpened(mineBoard)) {
            return false
        }

        if (isAllEmptyCellsOpened(mineBoard)) {
            result = GameResult.SUCCESS
            return false
        }
        return true
    }

    private fun isAnyMineCellOpened(mineBoard: MineBoard): Boolean {
        return false
    }

    private fun isAllEmptyCellsOpened(mineBoard: MineBoard): Boolean {
        return false
    }
}
