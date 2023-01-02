package minesweeper.domain.field

class Field(status: FieldStatus = FieldStatus.CLOSED, land: Land = BeforeLand()) {
    var status: FieldStatus = status
        private set
    var land: Land = land
        private set

    fun open() {
        status = FieldStatus.OPEN
    }

    fun isClosed() = status == FieldStatus.CLOSED
    fun isOpened() = status == FieldStatus.OPEN
    fun aroundMineCount() = land.aroundMineCount()

    fun landMine() {
        land = land.mine()
    }

    fun landSafe(aroundMineCount: Int) {
        land = land.safe(aroundMineCount)
    }

    override fun toString(): String {
        if (isClosed()) {
            return CLOSED
        }

        return land.toString()
    }

    companion object {
        private const val CLOSED = "C"
    }
}
