package mine.cell

class NoneCell(
    position: Position,
    val mineCount: Int,
) : Cell(position) {
    override fun name(): String {
        return NAME
    }

    companion object {
        const val NAME = "*"
    }
}
