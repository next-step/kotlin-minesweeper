package tdd.domain

data class Cell(
    var state: State = Empty
) {
    fun open(aroundMineCount: Int) {
        state = state.open(aroundMineCount)
    }

    fun isMine(): Boolean = state is Mine

    fun isOpened(): Boolean = state is Opened

    fun isZero(): Boolean = state == Opened.of(0)

    fun aroundMineCount(): Int = state.aroundMineCount()
}
