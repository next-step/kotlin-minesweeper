package mine.sweeper.domain

class SweeperMap(
    private val fields: Fields,
    private val randomPositions: MutableList<Pair<Int, Int>>,
) {
    fun entireMap(): List<Array<Field>> {
        return fields.entire().toList()
    }

    fun randomField(): Pair<Int, Int> {
        check(randomPositions.isNotEmpty())
        return randomPositions.removeFirst()
    }

    fun setMine(height: Int, width: Int) {
        fields.update(height, width, Field.MINE_FIELD)
    }
}
