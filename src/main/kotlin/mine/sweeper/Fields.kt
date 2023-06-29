package mine.sweeper

class Fields(option: MapOption) {
    private var fields = option.initMap()

    fun entire(): Array<Array<Field>> {
        return fields
    }

    fun find(height: Int, width: Int): Field {
        return fields[height][width]
    }

    fun changeField(height: Int, width: Int, mineField: Field) {
        fields[height][width] = mineField
    }
}
