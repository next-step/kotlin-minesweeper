package domain

class GeneralCell(
    private val aroundMineCount: Int = 0
) : Cell {

    private var open = false

    override fun getValue(): String = aroundMineCount.toString()

    override fun isMine(): Boolean {
        return false
    }

    override fun open(): Boolean {
        open = true

        return aroundMineCount == 0
    }

    override fun isOpen(): Boolean = open
}
