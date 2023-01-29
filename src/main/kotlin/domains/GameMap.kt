package domains

@JvmInline
value class GameMap private constructor(val value: List<Block>) {
    fun getBlockByPosition(position: Position): Block {
        return this.value.single { it.position == position }
    }

    companion object {
        fun from(gameSize: GameSize, minePositions: Positions): GameMap {
            val map: MutableList<Block> = mutableListOf()
            (0 until gameSize.width).forEach { width ->
                (0 until gameSize.height).forEach { height ->
                    val position = Position(width, height)
                    val block = Block.from(position, minePositions)
                    map.add(block)
                }
            }
            return GameMap(map.toList())
        }

    }
}
