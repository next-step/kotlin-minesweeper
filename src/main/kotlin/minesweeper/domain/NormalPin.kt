package minesweeper.domain

class NormalPin() : Pin() {
    var surroundMineNumber: Int = 0
        private set

    fun addSurroundMineNumber() {
        surroundMineNumber++
    }

    fun comparePinType(other: Pin) {
        if (other.isMinePin()) addSurroundMineNumber()
    }

    fun changeToMine(): MinePin {
        return MinePin()
    }

    override fun equals(other: Any?): Boolean {
        return this === other
    }

    override fun hashCode(): Int {
        return System.identityHashCode(this)
    }
}
