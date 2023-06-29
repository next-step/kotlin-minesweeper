package mine.sweeper.domain

class MapInitializer(
    private val height: Int,
    private val width: Int,
) {
    init {
        require(height > 0 && width > 0)
    }

    fun createMap(): SweeperMap {
        val fields = Fields(height, width)

        val randomPositions = MutableList(height * width) {
            Pair(it / width, it % width)
        }.shuffled().toMutableList()

        return SweeperMap(fields, randomPositions)
    }
}
