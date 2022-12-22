package minesweeper.domain

const val DEFAULT_MINE_CHARACTER = "*"

class MineBlock(override val mark: String = DEFAULT_MINE_CHARACTER) : Block() {
    override fun open() {
        TODO("Not yet implemented")
    }

    override fun toString(): String {
        return mark
    }
}
