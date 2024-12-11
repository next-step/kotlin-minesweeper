class RandomShuffleAlgorithm : ShuffleAlgorithm {
    override fun <T> shuffle(list: List<T>): List<T> = list.shuffled()
}
