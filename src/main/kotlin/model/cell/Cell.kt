package model.cell

data class Cell(val mineStatus: MineStatus, val position: Position) {
    override fun toString(): String {
        return mineStatus.toString()
    }
}
