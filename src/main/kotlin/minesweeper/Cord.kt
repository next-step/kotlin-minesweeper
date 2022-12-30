package minesweeper

class Cord(
    val x: Int,
    val y: Int
) {
    init {
        require(x >= 0) { "x좌표는 음수일 수 없습니다" }
        require(y >= 0) { "y좌표는 음수일 수 없습니다" }
    }
}

class CordBuilder(
    private var _x: Int = -1,
    private var _y: Int = -1
) {
    fun setX(input: Int): CordBuilder {
        return CordBuilder(_x = input, _y = _y)
    }

    fun setY(input: Int): CordBuilder {
        return CordBuilder(_x = _x, _y = input)
    }

    fun build(): Cord = Cord(_x, _y)
}
