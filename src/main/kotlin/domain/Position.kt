package domain

data class Position(private val x: Int, private val y: Int) {
    fun getX() = x
    fun getY() = y
}
