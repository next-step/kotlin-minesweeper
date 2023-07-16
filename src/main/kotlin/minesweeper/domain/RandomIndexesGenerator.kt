package minesweeper.domain

object RandomIndexesGenerator {
    fun generate(size: Int, maximum: Int): List<Int> {
        return (0..maximum)
            .shuffled()
            .take(size)
    }
}