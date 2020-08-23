package model.cell

data class Cell(val mineType: MineType, val position: Position) {
    override fun toString(): String {
        return mineType.toString()
    }
}
