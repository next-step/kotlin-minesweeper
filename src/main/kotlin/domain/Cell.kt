package domain

sealed class Cell {
    abstract var status: CellStatus
    var countOfMinesAround: Int = 0

    data class NormalCell(override var status: CellStatus = CellStatus.CLOSED) : Cell()
    data class MineCell(override var status: CellStatus = CellStatus.CLOSED) : Cell()

    companion object {
        fun createNormalCell(): Cell {
            return NormalCell()
        }

        fun createMineCell(): Cell {
            return MineCell()
        }
    }
}

enum class CellStatus {
    CLOSED,
    OPENED,
    FLAGGED,
}
