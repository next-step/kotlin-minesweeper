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
        return OpenStatus.from(nearMineCount, hasMine)
    }

    fun setNearMineCount(count: Int) {
        check(count in VALIDATE_NEAR_MINE_COUNT)
        nearMineCount = count
    }

    companion object {
        val VALIDATE_NEAR_MINE_COUNT = 0..8
    }
}

