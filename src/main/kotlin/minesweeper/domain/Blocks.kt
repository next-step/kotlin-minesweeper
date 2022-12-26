package minesweeper.domain

class Blocks(normalBlocks: List<Block>, mineBlocks: List<Block>) {
    val blocks: List<Block> = (normalBlocks + mineBlocks).shuffled()
}
