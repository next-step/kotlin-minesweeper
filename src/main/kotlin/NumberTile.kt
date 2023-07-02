class NumberTile(point: Point, isOpened: Boolean = false, value: Int = 0) : Tile(point, isOpened) {
    private var _value: Int = value
    val value: Int get() = _value

    fun updateValue(map: GameMap) {
        _value = map.mineCountInSquare(point)
    }
}
