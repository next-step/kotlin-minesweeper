package domains

class MapGenerator(
    private val gameSize: GameSize,
    private val minePositions: Positions
) {
    fun generateMap(): GameMap {
        val map: MutableList<Block> = mutableListOf()
        (0 until gameSize.width).forEach { width ->
            (0 until gameSize.height).forEach { height ->
                map.add(createBlock(width, height))
            }
        }
        return GameMap(map.toList())
    }

    private fun createBlock(width: Int, height: Int): Block {
        val position = Position(width, height)
        if (position in minePositions.values) {
            return MineBlock(position)
        }
        return NormalBlock(position)
    }
}
