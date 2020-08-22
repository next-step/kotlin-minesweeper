package model

object Winner {

    fun isLose(gamer: Gamer) = gamer.gameBoard.containsValue(MineType.MINE)
}
