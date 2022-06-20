package minesweeper.domain.common

interface Rectangle {
    val width: PositiveInt
    val height: PositiveInt
    val size: Int get() = (width * height).value
}
