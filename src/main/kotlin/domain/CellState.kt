package domain

sealed class CellState {
    abstract fun open(aroundMineCount: AroundMineCount): CellState

    abstract fun aroundMineCount(): AroundMineCount
}

object Closed : CellState() {
    override fun open(aroundMineCount: AroundMineCount): CellState = Opened(aroundMineCount)

    override fun aroundMineCount(): AroundMineCount {
        throw IllegalStateException("Closed 상태에서 주변 지뢰 개수를 반환할 수 없습니다")
    }
}

data class Opened(
    private val aroundMineCount: AroundMineCount,
) : CellState() {
    override fun open(aroundMineCount: AroundMineCount): CellState = this

    override fun aroundMineCount(): AroundMineCount = aroundMineCount
}
