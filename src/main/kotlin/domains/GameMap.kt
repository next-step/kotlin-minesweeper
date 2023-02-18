package domains

@JvmInline
value class GameMap private constructor(val value: Blocks) {
    fun getBlockByPosition(position: Position): Block {
        return this.value.getBlockByPosition(position)
    }

    fun setNormalBlocks() {
        val normalBlocks = this.value.findNormalBlocks()
        val mineBlocks = this.value.findMineBlocks()

        normalBlocks.forEach { normalBlock ->
            normalBlock.updateMarkerByAroundMines(mineBlocks)
        }
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
            return GameMap(Blocks(map.toList()))
        }
    }
}
