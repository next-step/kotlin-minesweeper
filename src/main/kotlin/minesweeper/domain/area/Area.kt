package minesweeper.domain.area

data class Area(private val _width: Width, private val _height: Height) {
    val width: Int get() = _width.width

    val height: Int get() = _height.height

    fun area(): Int = Math.multiplyExact(width, height)
}
