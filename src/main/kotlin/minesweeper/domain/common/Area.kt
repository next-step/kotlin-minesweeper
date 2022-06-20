package minesweeper.domain.common

data class Area(
    override val width: PositiveInt,
    override val height: PositiveInt
) : Rectangle {

    companion object {
        fun of(width: PositiveInt, height: PositiveInt): Area {
            return Area(width, height)
        }
    }
}
