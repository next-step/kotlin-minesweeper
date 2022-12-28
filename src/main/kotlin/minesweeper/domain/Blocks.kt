package minesweeper.domain

class Blocks(width: Int, height: Int, blocks: List<Block>) {
    val blockBoard: List<List<Block>>

    init {
        var blockIndex = 0
        blockBoard = List(width) { List(height) { blocks[blockIndex++] } }
    }
}
