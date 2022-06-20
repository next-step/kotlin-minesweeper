package minesweeper.domain.common

data class Area(
    override val width: PositiveInt,
    override val height: PositiveInt
) : Rectangle
