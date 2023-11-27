package minesweeper.model.point

class Points(
    private val points: Map<Coordinate, Attribute>
) {
    fun symbol(coordinate: Coordinate): String {
        return points[coordinate]?.symbol ?: Attribute.NONE.symbol
    }

    fun countOfMine(): Int {
        return points.values
            .count { it == Attribute.MINE }
    }
}
