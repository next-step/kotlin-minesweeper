package domain

class Spot(
    val hasMine: Boolean,
    private var status: SpotStatus = SpotStatus.CLOSED
) {

    private var nearMineCount: Int = 0

    fun isOpen(): Boolean {
        return status == SpotStatus.OPENED
    }

    fun open(): Int {
        status = SpotStatus.OPENED
        return nearMineCount
    }

    fun setNearMineCount(count: Int) {
        nearMineCount = count
    }
}

