package mine.sweeper.domain

class SafeField(position: Position) : Field(position) {
    var value = 0
        private set

    fun increase() {
        value++
    }
}
