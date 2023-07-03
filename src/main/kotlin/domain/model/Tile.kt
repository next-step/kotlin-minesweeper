package domain.model

sealed class Tile(val point: Point, isOpened: Boolean) {
    private var _isOpened: Boolean = isOpened
    val isOpened: Boolean get() = _isOpened
}
