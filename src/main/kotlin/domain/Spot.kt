package domain

import domain.status.Closed
import domain.status.Opened
import domain.status.SpotStatus

class Spot(
    val hasMine: Boolean,
    private var status: SpotStatus = Closed
) {

    private var nearMineCount: Int = 0

    fun isOpen(): Boolean = status !is Closed

    fun open(): SpotStatus {
        status = Opened.from(nearMineCount, hasMine)
        return status
    }

    fun setNearMineCount(count: Int) {
        check(count in VALIDATE_NEAR_MINE_COUNT)
        nearMineCount = count
    }

    fun viewSpot(): String = status.getSymbol()

    companion object {
        val VALIDATE_NEAR_MINE_COUNT = 0..8
    }
}
