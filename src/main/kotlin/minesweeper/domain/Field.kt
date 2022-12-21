package minesweeper.domain

open class Field

class Mine : Field() {
    override fun toString(): String {
        return MINE
    }

    companion object {
        private const val MINE = "*"
    }
}

class Safe(val aroundMineCount: Int) : Field() {
    override fun toString(): String {
        return aroundMineCount.toString()
    }
}
