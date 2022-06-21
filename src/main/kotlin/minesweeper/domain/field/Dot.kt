package minesweeper.domain.field

sealed interface Dot

object Mine : Dot

data class NonMine(val mineCount: Int) : Dot {

    fun addCount(): NonMine = NonMine(mineCount.inc())

    companion object {
        private const val DEFAULT_MINE_COUNT = 0
        fun init(): NonMine = NonMine(DEFAULT_MINE_COUNT)
    }
}
