package domain

class Cell {
    var status: CellStatus = CellStatus.CLOSED
    var mineStatus: MineStatus = MineStatus.Empty

    override fun toString(): String {
        return when (mineStatus) {
            MineStatus.Exists -> "*"
            MineStatus.Empty -> "C"
        }
    }

    fun setMine() {
        mineStatus = MineStatus.Exists
    }
}

enum class CellStatus {
    CLOSED,
    OPENED,
    FLAGGED,
}

sealed class MineStatus {
    object Empty : MineStatus()
    object Exists : MineStatus()
}
