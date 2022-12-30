package minesweeper

data class Cord(
    val x: Int,
    val y: Int
) {
    init {
        require(x >= 0) { "x좌표는 음수일 수 없습니다" }
        require(y >= 0) { "y좌표는 음수일 수 없습니다" }
    }
}

class CordBuilder(
    private val _x: Int = -1,
    private val _y: Int = -1
) {
    fun setX(input: Int): CordBuilder {
        return CordBuilder(input, _y)
    }

    fun setX(range: IntRange): List<CordBuilder> {
        return range.map { newX -> CordBuilder(newX, _y) }
    }

    fun setY(newY: Int): CordBuilder {
        return CordBuilder(_x, newY)
    }

    fun setY(newYRange: IntRange): List<CordBuilder> {
        return newYRange.map { newY -> CordBuilder(_x, newY) }
    }

    fun build(): Cord = Cord(_x, _y)
}
