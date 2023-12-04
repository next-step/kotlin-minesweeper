package domain

class Column<T>(
    private val list: List<T>
) : List<T> by list
