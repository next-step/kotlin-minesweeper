package mine.sweeper.domain

class SweeperMap(setup: MapSetup) {
    private val fields: Fields = Fields(setup.initFields())
    private val randomPositions: MutableList<Pair<Int, Int>> = setup.initRandomPositions()

    fun entireMap(): Array<Array<Field>> {
        return fields.entire()
    }

    fun randomField(): Pair<Int, Int> {
        check(randomPositions.isNotEmpty())
        return randomPositions.removeFirst()
    }

    fun setMine(height: Int, width: Int) {
        fields.changeField(height, width, Field.MINE_FIELD)
    }
}
