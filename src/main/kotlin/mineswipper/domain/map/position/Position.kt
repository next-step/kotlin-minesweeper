package mineswipper.domain.map.position

data class Position(
    val x: Int,
    val y: Int
) {
    fun getAroundPositions(size: Size): Positions {
        return (x - 1..x + 1).flatMap { xVal ->
            (y - 1..y + 1).map { yVal -> Position(xVal, yVal) }
        }.filter { it.isValid(size.width, size.height) && it != this }.toPositions()
    }

    fun toRow(): Row {
        return Row(y)
    }

    private fun isValid(width: Int, height: Int): Boolean {
        return x in 0 until width && y in 0 until height
    }


}

fun List<Position>.toPositions(): Positions = Positions(this)
