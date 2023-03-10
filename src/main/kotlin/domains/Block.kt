package domains

abstract class Block constructor(open val position: Position) {
    open var marker: String = "C"
    var isOpened: Boolean = false
    protected abstract fun openBlock()

    fun open() {
        openBlock()
        isOpened = true
    }

    companion object {
        fun from(position: Position, minePositions: Positions): Block {
            if (position in minePositions.values) {
                return MineBlock(position)
            }
            return NormalBlock(position)
        }
    }
}
