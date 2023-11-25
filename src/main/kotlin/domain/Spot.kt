package domain

class Spot(private val status: MineStatus) {

    fun spotSymbol(nearMineCount: Int): String =
        OpenStatus.from(status, nearMineCount).symbol

    fun isMine(): Boolean =
        status == MineStatus.MINED
}
