package domain.collections

fun <T> nestedList(
    columnSize: Int,
    rowSize: Int,
    action: (col: Int, row: Int) -> T
): List<List<T>> {
    return List(columnSize) { column -> rows(column, rowSize, action) }
}

private fun <T> rows(
    column: Int,
    rowSize: Int,
    action: (col: Int, row: Int) -> T
): List<T> {
    return List(rowSize) { row -> action(column, row) }
}
