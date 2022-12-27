package domain

sealed class Field

class Land(
    isOpened: Boolean = false
) : Field() {
    var isOpened = isOpened
        private set

    fun open() {
        isOpened = true
    }
}

class Mine : Field()
