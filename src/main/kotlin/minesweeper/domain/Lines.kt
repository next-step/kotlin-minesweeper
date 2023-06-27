package minesweeper.domain

class Lines(private val values: List<Line>) : List<Line> by values {

    operator fun contains(point: Point): Boolean {
        val line = values.firstOrNull() ?: return false

        return point.y in (0 until size) && point.x in (0 until line.size)
    }

    fun findPoint(point: Point): SymbolPoint =
        if (point.y in (0 until this.size)) values[point.y].findPoint(point)
        else throw IndexOutOfBoundsException("적절한 좌표가 아닙니다. [line size:${this.size}, input index: ${point.y}]")
}
