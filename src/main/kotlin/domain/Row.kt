package domain

typealias Row = MutableList<Cell>

fun Row(vararg cell: Cell): Row {
    return cell.toMutableList()
}

inline fun Row(columnCount: Int, createValue: (Int) -> Cell): Row {
    return MutableList(columnCount, createValue)
}
