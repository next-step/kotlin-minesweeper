package model

object Winner {

    fun isLose(gamer: Gamer): Boolean {
        val isNotFinished = gamer.gameBoard.containsValue(MineType.NONE)
        val isMineClicked = gamer.gameBoard.containsValue(MineType.MINE)
        return isNotFinished && isMineClicked
    }
}
