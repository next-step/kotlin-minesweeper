package vo

class Spot(private val status: MineStatus) {

    fun isMineOn(): String = OpenStatus.from(status).symbol
}
