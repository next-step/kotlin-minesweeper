package model

const val MINE_STRING = "*"
const val NOT_MINE_STRING = "C"

data class Cell(val isMine: Boolean, val position: Position) {
    var isClick = false
        private set
    var aroundMineCount: Int = 0
        private set

    fun addCount() {
        aroundMineCount++
    }

    fun match(position: Position): Boolean {
        return position == this.position
    }

    fun click() {
        isClick = true
    }

    override fun toString(): String {
        return return when {
            isClick && isMine -> MINE_STRING
            isClick && !isMine -> aroundMineCount.toString()
            else -> NOT_MINE_STRING
        }
    }
}
