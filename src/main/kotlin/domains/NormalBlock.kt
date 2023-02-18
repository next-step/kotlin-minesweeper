package domains

class NormalBlock(override val position: Position) : Block(position) {
    override var marker: String = "0"

    fun updateMarkerByAroundMines(mineBlocks: List<MineBlock>) {
        val aroundPositions = getAroundPositions()
        val aroundMineCount = mineBlocks
            .map { mineBlock -> aroundPositions.contains(mineBlock.position) }
            .count { it }
        marker = aroundMineCount.toString()
    }

    private fun getAroundPositions(): List<Position> {
        val aroundX = listOf(1, 0, -1)
        val aroundY = listOf(1, 0, -1)
        return aroundX.map { x ->
            aroundY.map { y ->
                val movedX = position.x + x
                val movedY = position.y + y
                Position(movedX, movedY)
            }
        }.flatten()
    }
}
