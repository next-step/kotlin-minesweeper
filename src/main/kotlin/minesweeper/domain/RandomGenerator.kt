package minesweeper.domain

interface RandomGenerator {

    fun generate(start: Int = 0, until: Int, count: Int): List<Int>
}

object DefaultRandomGenerator : RandomGenerator {

    override fun generate(start: Int, until: Int, count: Int): List<Int> {
        return (start until until).shuffled().take(count)
    }
}
