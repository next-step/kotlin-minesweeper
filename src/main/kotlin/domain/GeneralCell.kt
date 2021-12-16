package domain

class GeneralCell(
    private val aroundMineCount: Int = 0,
    private val position: Position = Position(0, 0),
) : Cell {

    private var open = false

    override fun getValue(): String = aroundMineCount.toString()

    override fun isMine(): Boolean {
        return false
    }

    override fun open(): Boolean {
        open = true

        return true
    }

    override fun isOpen(): Boolean = open

    override fun getOpenableAroundPosition(): List<Position> {
        return when (aroundMineCount == 0) {
            true -> position.getAroundPositions()
            false -> emptyList()
        }
    }
}
