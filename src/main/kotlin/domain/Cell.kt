package domain

class Cell {
    var status: CellStatus = CellStatus.CLOSED
    var isMine: Boolean = false

    override fun toString(): String {
        return when (isMine) {
            true -> "*"
            false -> "C"
        }
    }
}

enum class CellStatus {
    CLOSED,
    OPENED,
    FLAGGED
}
