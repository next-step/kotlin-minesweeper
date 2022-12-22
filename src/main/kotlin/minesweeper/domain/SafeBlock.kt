package minesweeper.domain

const val DEFAULT_SAFE_BLOCK_CHARACTER = "C"

class SafeBlock(override val mark: String = DEFAULT_SAFE_BLOCK_CHARACTER) : Block() {
    override fun open() {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return mark
    }
}
