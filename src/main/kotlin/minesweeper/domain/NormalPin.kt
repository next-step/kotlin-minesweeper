package minesweeper.domain

class NormalPin() : Pin() {
    var mineNumber: Int = 0
        private set

    fun addMineNumber() {
        mineNumber++
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}
