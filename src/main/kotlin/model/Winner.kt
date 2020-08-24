package model

object Winner {

    fun isLose(gamer: Gamer): Boolean {
        val isNotFinished = gamer.board.map.containsValue(MineType.NONE)
        val isMineClicked = gamer.board.map.containsValue(MineType.MINE)
        return isNotFinished && isMineClicked
    }
}
