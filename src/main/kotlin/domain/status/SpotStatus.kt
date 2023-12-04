package domain.status

interface SpotStatus {

    fun getSymbol(): String

    fun mineTrapped(): Boolean = false
}
