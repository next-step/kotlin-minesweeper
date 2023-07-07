package domain

class Cell {
    var status: CellStatus = CellStatus.CLOSED
    var isMine: Boolean = false
}

enum class CellStatus {
    CLOSED,
    OPENED,
    FLAGGED
}
