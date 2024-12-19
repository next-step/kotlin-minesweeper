package minesweeper.domain.strategy

interface ShuffleAlgorithm {
    fun <T> shuffle(list: List<T>): List<T>
}
