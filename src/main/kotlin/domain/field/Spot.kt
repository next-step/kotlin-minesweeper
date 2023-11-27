package domain.field

import domain.status.MineStatus
import domain.status.OpenStatus

class Spot(private val status: MineStatus) {

    private var openStatus = OpenStatus.COVERED

    fun spotSymbol(): String = openStatus.symbol

    fun isMine(): Boolean =
        status == MineStatus.MINED
}
