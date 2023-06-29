package mine.sweeper

class Fields(private val fields: Array<Array<Field>>) {

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
