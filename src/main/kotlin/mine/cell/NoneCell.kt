package mine.cell

class NoneCell(
    position: Position,
    private val mineCount: Int,
) : Cell(position) {
    override fun name(): String = if (isClicked) mineCount.toString() else super.name()

    override fun isNearMine(): Boolean = mineCount > 0

    override fun isMineCell(): Boolean = false
}
