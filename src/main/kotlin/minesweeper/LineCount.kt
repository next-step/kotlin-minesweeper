package minesweeper

@JvmInline
value class LineCount(val count: Int) {
    init {
        require(count > 0) {
            "Line Count must be positive"
        }
    }
}
