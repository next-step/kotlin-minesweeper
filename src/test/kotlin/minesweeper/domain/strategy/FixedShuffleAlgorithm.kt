package minesweeper.domain.strategy

class FixedShuffleAlgorithm : ShuffleAlgorithm {
    override fun <T> shuffle(list: List<T>): List<T> = list
}
