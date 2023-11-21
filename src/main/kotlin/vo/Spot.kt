package vo

class Spot(private val isMine: Boolean) {

    fun isMineOn(): String = if (isMine) "*" else "O"
}
