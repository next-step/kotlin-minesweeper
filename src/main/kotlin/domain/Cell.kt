package domain

class Cell {
    var status: CellStatus = CellStatus.CLOSED
}

enum class CellStatus {
    CLOSED,
    OPENED,
    FLAGGED
}
