package minesweeper

@JvmInline
value class MineCount(val count: Int) {
    init {
        require(count > 0) {
            "Mine Number must be positive"
        }
    }
}
