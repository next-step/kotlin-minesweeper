package domain

class Mines(private val values: List<Mine>) : List<Mine> by values {
    fun isMine(row: Int, col: Int): Boolean {
        return values.any { it.position.row == row && it.position.col == col }
    }
}
