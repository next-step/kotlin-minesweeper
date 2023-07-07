package tdd.domain

sealed class State {
    abstract fun open(aroundMineCount: Int): State
}

sealed class Closed : State()

object Empty : Closed() {
    override fun open(aroundMineCount: Int): State = Opened(aroundMineCount)
}

object Mine : Closed() {
    override fun open(aroundMineCount: Int): State {
        throw IllegalStateException("지뢰를 열 수 없습니다")
    }
}

data class Opened(
    val aroundMineCount: Int,
) : State() {
    override fun open(aroundMineCount: Int): State = this
}
