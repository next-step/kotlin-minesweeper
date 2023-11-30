package domain

class Spot(private var status: SpotStatus = SpotStatus.CLOSED) {

    fun isOpen(): Boolean {
        return status == SpotStatus.OPENED
    }

    fun open() {
        status = SpotStatus.OPENED
    }
}
