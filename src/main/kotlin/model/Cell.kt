package model

const val MINE_STRING = "*"

data class Cell(val isMine: Boolean, val position: Position) {
    var aroundMineCount: Int = 0
        private set

    fun addCount() {
        aroundMineCount++
    }

    fun match(cell: Cell): Boolean {
        return cell == this
    }

    override fun toString(): String {
        return if (isMine) MINE_STRING else aroundMineCount.toString()
    }
}
