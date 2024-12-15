package minesweeper.domain

class FixedShuffleAlgorithm : ShuffleAlgorithm {
    override fun <T> shuffle(list: List<T>): List<T> = list
}
