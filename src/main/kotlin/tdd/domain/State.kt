package tdd.domain

sealed class State {
    abstract fun open(aroundMineCount: Int): State

    abstract fun aroundMineCount(): Int
}

sealed class Closed : State() {
    override fun aroundMineCount(): Int {
        throw IllegalStateException("Closed 상태에서는 주변 지뢰 개수를 구할 수 없습니다.")
    }
}

object Empty : Closed() {
    override fun open(aroundMineCount: Int): State = Opened.of(aroundMineCount)
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

    override fun aroundMineCount(): Int = aroundMineCount

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
