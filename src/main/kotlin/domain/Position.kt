package domain

data class Position(val x: Int, val y: Int) {
    fun getArounds(width: Int, height: Int): List<Position> {
        return (x - 1..x + 1).flatMap { xVal ->
            (y - 1..y + 1).map { yVal -> Position(xVal, yVal) }
        }.filter { it != this && it.isValid(width, height) }
    }

    private fun isValid(width: Int, height: Int): Boolean {
        return x in 0 until width && y in 0 until height
    }
}
