package minesweeper.domain.land.state

fun Area(widht: Int, height: Int): Area = Area(Size(widht), Size(height))

data class Area(private val _width: Size, private val _height: Size) {
    val width: Int
        get() = _width.value

    val height: Int
        get() = _height.value

    fun size() = width * height
}
