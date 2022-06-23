package minesweeper.domain

private enum class CellStatus {
    Show, Hide
}

sealed class Cell(private var status: CellStatus = CellStatus.Hide) {
    abstract val coordinate: Coordinate

    fun isOpened() = status == CellStatus.Show

    fun open() {
        status = CellStatus.Show
    }

    abstract fun isNearMine(): Boolean

    data class Block(override val coordinate: Coordinate, val aroundMineCount: Int = 0) : Cell() {
        override fun isNearMine() = aroundMineCount > 0
    }

    data class Mine(override val coordinate: Coordinate) : Cell() {
        override fun isNearMine(): Boolean = true
    }
}
