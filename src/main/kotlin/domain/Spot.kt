package domain

class Spot(private val status: SpotStatus = SpotStatus.CLOSED) {

    fun isOpen(): Boolean {
        return status == SpotStatus.OPENED
    }
}
