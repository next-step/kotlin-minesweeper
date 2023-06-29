package mine.sweeper

class SweeperMap(height: Int, width: Int) {
    private val places: List<List<Field>>
    init {
        require(height > 0 || width > 0)
        places = List(height) {
            List(width) {
                Field.SAFE_FIELD
            }
        }
    }

    fun entireMap(): List<List<Field>> {
        return places
    }
}
