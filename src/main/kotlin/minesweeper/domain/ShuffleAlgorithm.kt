package minesweeper.domain

interface ShuffleAlgorithm {
    fun <T> shuffle(list: List<T>): List<T>
}
