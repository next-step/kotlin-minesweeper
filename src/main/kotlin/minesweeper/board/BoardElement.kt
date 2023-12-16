package minesweeper.board

data class BoardElement (
    private val _height: Element,
    private val _width: Element
) {
    val height: Int
        get() = _height.value

    val width: Int
        get() = _width.value
}
