package domain

data class Row(val cells: List<Cell>) {

    val size: Int = cells.size

    val ordered: Row
        get() = Row(cells.sortedBy { it.coordinate.x })

    init {
        require(cells.isNotEmpty()) { "하나 이상의 cell 이 필요합니다" }
        require(cells.distinctBy { it.coordinate }.size == size) { "중복된 cell 이 있습니다" }
        require(cells.map { it.coordinate.y }.distinct().size == 1) { "y 값은 동일해야 합니다" }
    }
}
