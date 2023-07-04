package domain.model

class NumberTile(point: Point, isOpened: Boolean = false, value: Int = EMPTY) : Tile(point, isOpened) {
    private var _value: Int = value
    val value: Int get() = _value

    fun updateValue(value: Int) {
        _value = value
    }

    companion object {
        const val EMPTY = 0
    }
}
