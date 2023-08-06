package domain

data class Row(
    private val _values: MutableList<Cell>
) {
    val values: List<Cell>
        get() = _values.toList()
    val size: Int
        get() = _values.size

    operator fun get(x: Int): Cell {
        return _values[x]
    }

    operator fun set(x: Int, value: Cell) {
        _values[x] = value
    }
}
