package mine.sweeper.domain

class Fields(height: Int, width: Int) {
    private val fields = Array(height) {
        Array(width) {
            Field.SAFE_FIELD
        }
    }

    fun entire(): Array<Array<Field>> {
        return fields
    }

    fun update(height: Int, width: Int, mineField: Field) {
        fields[height][width] = mineField
    }
}
