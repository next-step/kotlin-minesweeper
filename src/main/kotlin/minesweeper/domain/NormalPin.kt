package minesweeper.domain

class NormalPin() : Pin() {
    var surroundMineNumber: Int = 0
        private set

    fun addSurroundMineNumber() {
        surroundMineNumber++
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}
