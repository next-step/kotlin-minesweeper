package domain

data class Position(private val x: Int, private val y: Int) {
    companion object {
        fun getInstance(x: Int, y: Int): Position {
            return Position(x, y)
        }
    }
}
