package domain

class MineCell : Cell {

    private var open = false

    override fun open(): Boolean {
        return false
    }

    override fun isOpen(): Boolean = open

    override fun getValue(): String = "X"

    override fun isMine(): Boolean {
        return true
    }
}
