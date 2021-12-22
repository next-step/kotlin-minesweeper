package domain

class MineCell : Cell {

    private var open = false

    override fun open(): Boolean {
        return false
    }

    override fun isOpen(): Boolean = open
    override fun getOpenableAroundPosition(): List<Position> = emptyList()

    override fun getValue(): String = "C"

    override fun isMine(): Boolean {
        return true
    }
}
