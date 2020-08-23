package model

object Winner {

    fun isLose(gamer: Gamer): Boolean {
        val isNotFinished = gamer.board.value.containsValue(MineType.NONE)
        val isMineClicked = gamer.board.value.containsValue(MineType.MINE)
        return isNotFinished && isMineClicked
    }
}
