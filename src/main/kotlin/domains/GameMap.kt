package domains

@JvmInline
value class GameMap private constructor(val value: Blocks) {

    init {
        this.setNormalBlocks()
    }

    fun getBlockByPosition(position: Position): Block {
        return this.value.getBlockByPosition(position)
    }

    private fun setNormalBlocks() {
        val normalBlocks = this.value.findNormalBlocks()
        val mineBlocks = this.value.findMineBlocks()

        normalBlocks.forEach { normalBlock ->
            normalBlock.updateMarkerByAroundMines(mineBlocks)
        }
    }

    companion object {
        fun from(gameSize: GameSize, minePositions: Positions): GameMap {
            val blocks: MutableList<Block> = mutableListOf()
            repeat(gameSize.width) { width ->
                blocks.addAll(generateBlocks(width, gameSize.height, minePositions))
            }
            return GameMap(Blocks(blocks.toList()))
        }

        private fun generateBlocks(
            currentWidth: Int,
            maxHeight: Int,
            minePositions: Positions,
        ): List<Block> {
            return (0 until maxHeight).map { height ->
                val position = Position(currentWidth, height)
                Block.from(position, minePositions)
            }.toList()
        }
    }
}
