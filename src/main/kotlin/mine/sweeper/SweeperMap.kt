package mine.sweeper

class SweeperMap(private val option: MapOption) {
    private val fields: Fields = Fields(option)
    private val randomPosition: MutableList<Pair<Int, Int>> = option.randomPosition()

    fun entireMap(): Array<Array<Field>> {
        return fields.entire()
    }

    fun randomField(): Pair<Int, Int> {
        check(randomPosition.isNotEmpty())
        return randomPosition.removeFirst()
    }

    fun setMine(height: Int, width: Int) {
        fields.changeField(height, width, Field.MINE_FIELD)
    }
}
