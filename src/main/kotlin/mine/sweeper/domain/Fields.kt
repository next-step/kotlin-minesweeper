package mine.sweeper.domain

class Fields(val fieldList: List<Field>) {
    val size = fieldList.size

    infix operator fun get(position: Position): Field? {
        return fieldList.find { it.isSame(position) }
    }
}
