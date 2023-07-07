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

    companion object {
        private const val MIN_AROUND_MINE_COUNT = 0
        private const val MAX_AROUND_MINE_COUNT = 8
        private val aroundMineCounts: Map<Int, Opened> =
            (MIN_AROUND_MINE_COUNT..MAX_AROUND_MINE_COUNT).associateWith { Opened(it) }

        fun of(aroundMineCount: Int): Opened {
            return aroundMineCounts[aroundMineCount]
                ?: throw IllegalStateException("주변 지뢰 개수가 $MIN_AROUND_MINE_COUNT ~ $MAX_AROUND_MINE_COUNT 범위를 초과했습니다.")
        }
    }
}
