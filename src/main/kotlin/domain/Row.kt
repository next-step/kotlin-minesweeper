package domain

class Row<T>(
    private val list: List<Column<T>>
) : List<Column<T>> by list
