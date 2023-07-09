package mine.sweeper.domain

open class Field(val position: Position) {
    fun isSame(target: Position): Boolean {
        return position == target
    }
}
