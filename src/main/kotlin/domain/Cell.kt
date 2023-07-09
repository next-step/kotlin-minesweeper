package domain

class Cell {
    private var status: CellStatus = CellStatus.CLOSED
    private var mineStatus: MineStatus = MineStatus.Empty

    fun setMine() {
        mineStatus = MineStatus.Exists
    }

    fun getMineStatus(): MineStatus {
        return mineStatus
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
