package domain

class Spot(
    val hasMine: Boolean,
    private var status: SpotStatus = SpotStatus.CLOSED
) {

    private var nearMineCount: Int = 0

    fun isOpen(): Boolean {
        return status == SpotStatus.OPENED
    }

    fun open(): OpenStatus {
        status = SpotStatus.OPENED
        return getOpenStatus()
    }

    fun setNearMineCount(count: Int) {
        check(count in VALIDATE_NEAR_MINE_COUNT)
        nearMineCount = count
    }

    fun getOpenStatus(): OpenStatus = OpenStatus.from(nearMineCount, hasMine)

    fun viewSpot(): String =
        if (isOpen()) OpenStatus.from(nearMineCount, hasMine).symbol else "X"

    companion object {
        val VALIDATE_NEAR_MINE_COUNT = 0..8
    }
}

