package minesweeper.domain

sealed class Block {
    abstract fun open()
}

class MineBlock : Block() {
    override fun open() {
        TODO("Not yet implemented")
    }
}

class SafeBlock : Block() {
    override fun open() {
        TODO("Not yet implemented")
    }
}
