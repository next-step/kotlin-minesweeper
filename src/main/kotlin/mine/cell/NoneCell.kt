package mine.cell

class NoneCell(
    position: Position,
    val mineCount: Int,
) : Cell(position) {
    var isClicked: Boolean = false

    override fun name(): String {
        return if (isClicked) mineCount.toString() else super.name()
    }
}
