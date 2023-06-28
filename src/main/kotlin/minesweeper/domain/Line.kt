package minesweeper.domain

@JvmInline
value class Line(private val symbolPoints: List<SymbolPoint>) : List<SymbolPoint> by symbolPoints {
    fun findPoint(point: Point): SymbolPoint =
        if (point.x in (0 until this.size))
            symbolPoints[point.x]
        else
            throw IndexOutOfBoundsException("적절한 좌표가 아닙니다. [line size:${this.size}, input index: ${point.x}]")

    companion object {
        fun createCoverLine(indexY: Int, size: Int): Line = List(size) {
            SymbolPoint(x = it, y = indexY, symbol = SymbolType.BLIND)
        }.let(::Line)
    }
}
