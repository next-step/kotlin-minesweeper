package domain

sealed class CellState {
    abstract fun open(aroundMineCount: Int): CellState

    abstract fun aroundMineCount(): Int
}

object Closed : CellState() {
    override fun open(aroundMineCount: Int): CellState = Open(aroundMineCount)

    override fun aroundMineCount(): Int {
        throw IllegalStateException("Closed 상태에서 주변 지뢰 개수를 반환할 수 없습니다")
    }
}

data class Open(
    private val aroundMineCount: Int,
) : CellState() {
    override fun open(aroundMineCount: Int): CellState = this

    override fun aroundMineCount(): Int = aroundMineCount
}
