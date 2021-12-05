package mine.cell

class NoneCell(
    position: Position,
) : Cell(position) {
    var aroundMineCount: Int = 0

    fun changeAroundCount(count: Int) {
        aroundMineCount = count
    }

    override fun name(): String {
        return NAME
    }

    companion object {
        const val NAME = "*"
    }
}
