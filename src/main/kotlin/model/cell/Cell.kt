package model.cell

data class Cell(val mineType: MineType, val position: Position) {
    var aroundMineCount: Int = 0
        private set

    fun addCount() {
        aroundMineCount++
    }

    fun match(position: Position): Boolean {
        return position == this.position
    }

    override fun toString(): String {
        return if (mineType == MineType.MINE) MINE_STRING else aroundMineCount.toString()
    }
}
