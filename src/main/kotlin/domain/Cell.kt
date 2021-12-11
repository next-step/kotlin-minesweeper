package domain

interface Cell {
    fun getValue(): String
    fun isMine(): Boolean
    fun open(): Boolean
    fun isOpen(): Boolean
}
