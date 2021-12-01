package mine.cell

class MineCell(position: Position): Cell(position) {
    override fun name(): String {
        return NAME
    }

    companion object {
        const val NAME = "E"
    }
}
