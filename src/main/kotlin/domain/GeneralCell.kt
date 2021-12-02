package domain

class GeneralCell(
    private val aroundMineCount: Int = 0
) : Cell {

    override fun getValue(): String = aroundMineCount.toString()

    override fun isMine(): Boolean {
        return false
    }
}
