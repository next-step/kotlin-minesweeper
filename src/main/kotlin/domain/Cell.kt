package domain

class Cell(var hint: Int? = null, var isMine: Boolean = false, var isOpened: Boolean = false) {
    fun open() { isOpened = true }
    fun isClosed() = !isOpened
}
