package domain

data class CellGroup(private val cells: List<Cell>) {

    val size: Int = cells.size

    init {
        require(cells.isNotEmpty()) { "하나 이상의 cell 이 필요합니다" }
        require(cells.distinctBy { it.coordinate }.size == size) { "중복된 cell 이 있습니다" }
    }
}
