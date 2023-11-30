package domain

class Spot(
    val hasMine: Boolean,
    private var status: SpotStatus = SpotStatus.CLOSED
) {

    fun isOpen(): Boolean {
        return status == SpotStatus.OPENED
    }

    fun open() {
        status = SpotStatus.OPENED
    }
}

