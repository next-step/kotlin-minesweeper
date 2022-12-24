package domain.pos

sealed interface Pos {
    val value: Int

    fun isPossiblePlus(target: Pos): Boolean = value + target.value >= 0

    operator fun plus(target: Pos): Pos = AbstractPos.of(value + target.value)
}
