package mine.sweeper

class MapOption(
    private val height: Int,
    private val width: Int,
) {
    init {
        require(height > 0 && width > 0)
    }

    fun initMap(): Array<Array<Field>> {
        return Array(height) {
            Array(width) {
                Field.SAFE_FIELD
            }
        }
    }

    fun randomPosition(): MutableList<Pair<Int, Int>> {
        return MutableList(height * width) {
            Pair(it / width, it % width)
        }.shuffled().toMutableList()
    }
}
