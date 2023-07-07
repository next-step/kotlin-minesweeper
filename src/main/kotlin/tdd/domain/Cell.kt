package tdd.domain

data class Cell(
    private var state: State = Empty
) {
    fun state(): State = state

    fun open(aroundMineCount: Int) {
        if (isOpened() || isMine()) return

        state = state.open(aroundMineCount)
    }

    fun isMine(): Boolean = state is Mine

    fun isOpened(): Boolean = state is Opened

    fun isZero(): Boolean = state == Opened.of(0)

    fun aroundMineCount(): Int = state.aroundMineCount()

    companion object {
        fun of(isMine: Boolean): Cell {
            return if (isMine) Cell(Mine) else Cell()
        }
    }
}
