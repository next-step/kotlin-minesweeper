package minesweeper.domain

class Blocks(width: Int, height: Int, blocks: List<Block>) {
    val blockBoard: Array<Array<Block>>

    init {
        var blockIndex = 0
        blockBoard = Array(width) { Array(height) { blocks[blockIndex++] } }
    }
}
