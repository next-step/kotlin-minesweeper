package domain

class Mines(private val values: List<Mine>) : List<Mine> by values {
    fun isMine(position: Position): Boolean {
        return values.any { it.position == position }
    }
}
