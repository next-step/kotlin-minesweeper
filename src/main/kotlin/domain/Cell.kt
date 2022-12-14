package domain

class Cell(
    val pos: Pos,
    private var _block: Block
) {
    val block
        get() = _block

    fun updateBlock(block: Block) {
        _block = block
    }

    companion object {
        fun init(value: Int): Cell {
            return Cell(Pos.of(value), Land())
        }
    }
}
