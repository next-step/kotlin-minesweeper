package mine.sweeper.domain.value

class Field(var value: String = "0") {
    fun mineField() {
        value = "*"
    }
}
