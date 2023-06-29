package mine.sweeper

class SweeperMapSetup(
    private val height: Int,
    private val width: Int,
) {
    init {
        require(height > 0 && width > 0)
    }

    fun initFields(): Array<Array<Field>> {
        return Array(height) {
            Array(width) {
                Field.SAFE_FIELD
            }
        }
    }

    fun initRandomPositions(): MutableList<Pair<Int, Int>> {
        return MutableList(height * width) {
            Pair(it / width, it % width)
        }.shuffled().toMutableList()
    }
}
