package model

data class MineBoardProvider(
    private val length: Int,
    private val width: Int,
    private val defaultMark: MineMark = MineMark.SAFETY,
) {

    init {
        require(width > 0) { "width must be positive. but provided `$width`" }
        require(length > 0) { "length must be positive. but provided `$length`" }
    }

    val mineBoard: MineBoard
        get() = MineBoard(
            (0 until width).flatMap { x ->
                (0 until length).map { y -> Position(x, y) }
            }.associateWith { defaultMark }
        )
}
