package tdd.domain

data class Cell(
    var state: State = Empty
) {
    fun open(aroundMineCount: Int) {
        state = state.open(aroundMineCount)
    }

    fun isMine(): Boolean = state is Mine
}
