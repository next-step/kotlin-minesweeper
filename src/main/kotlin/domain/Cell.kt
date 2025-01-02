package domain

sealed interface Cell {
    val coordinate: Coordinate
    val status: CellStatus

    fun isMineCell(): Boolean

    fun open()

    fun isOpened(): Boolean {
        return status == CellStatus.OPEN
    }

    class MineCell(
        override val coordinate: Coordinate,
        private var _status: CellStatus = CellStatus.CLOSED,
    ) : Cell {
        override val status: CellStatus
            get() = _status

        override fun isMineCell(): Boolean = true

        override fun open() {
            _status = CellStatus.OPEN
        }
    }

    class EmptyCell(
        override val coordinate: Coordinate,
        private var _status: CellStatus = CellStatus.CLOSED,
    ) : Cell {
        override val status: CellStatus
            get() = _status

        override fun isMineCell(): Boolean = false

        override fun open() {
            _status = CellStatus.OPEN
        }
    }
}
