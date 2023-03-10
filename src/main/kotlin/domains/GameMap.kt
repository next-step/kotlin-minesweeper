package domains

class GameMap private constructor(val gameSize: GameSize, val blocks: Blocks) {
    init {
        this.setNormalBlocks()
    }

    fun checkOpenedMineBlock(): OpenResult {
        return OpenResult.fromMineBlockCheck(blocks.isMineOpen())
    }

    fun isWin(): OpenResult {
        return OpenResult.fromNormalBlockCheck(blocks.isAllOpenNormalBlock())
    }

    fun getBlockByPosition(position: Position): Block {
        return this.blocks.getBlockByPosition(position)
    }

    fun checkAlreadyOpened(position: Position): Boolean {
        return getBlockByPosition(position).isOpened
    }

    fun open(position: Position) {
        val block = getBlockByPosition(position)
        block.open()
        if (block is NormalBlock) {
            if (block.isZeroSurroundingMines()) {
                openNormalBlock(block)
            }
        }
    }

    private fun openNormalBlock(block: NormalBlock) {
        val que = ArrayDeque<NormalBlock>()
        que.add(block)
        while (que.isNotEmpty()) {
            val normalBlock = que.removeFirst()
            if (normalBlock.isZeroSurroundingMines()) {
                val positions = normalBlock.position.getSurroundingPositions(gameSize)
                val blocks = positions.values
                    .map { getBlockByPosition(it) }
                    .map { it as NormalBlock }
                val isZeroSurroundingMinesBlocks = blocks.filter { it.isZeroSurroundingMines() && !it.isOpened }
                blocks.forEach { it.open() }
                que.addAll(isZeroSurroundingMinesBlocks)
            }
        }
    }

    private fun setNormalBlocks() {
        val normalBlocks = this.blocks.findNormalBlocks()
        val mineBlocks = this.blocks.findMineBlocks()

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
            return GameMap(gameSize, Blocks(blocks.toList()))
        }

        private fun generateBlocks(
            currentWidth: Int,
            maxHeight: Int,
            minePositions: Positions,
        ): List<Block> {
            return (0 until maxHeight).map { height ->
                val position = Position.fromApplication(currentWidth, height)
                Block.from(position, minePositions)
            }.toList()
        }
    }
}
