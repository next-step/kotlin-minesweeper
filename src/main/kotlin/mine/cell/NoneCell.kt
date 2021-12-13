package mine.cell

class NoneCell(
    position: Position,
    private val mineCount: Int,
) : Cell(position) {
    override fun isNearMine(): Boolean = mineCount > 0

    override fun isMineCell(): Boolean = false

    fun getNearMineCount(): Int = mineCount
}
