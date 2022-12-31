package minesweeper.domain.tile

sealed class SurroundingTiles {
    object TopLeft : SurroundingTiles()
    object TopMiddle : SurroundingTiles()
    object TopRight : SurroundingTiles()
    object MiddleLeft : SurroundingTiles()
    object MiddleRight : SurroundingTiles()
    object BottomLeft : SurroundingTiles()
    object BottomMiddle : SurroundingTiles()
    object BottomRight : SurroundingTiles()

    companion object {
        fun values() = listOf(TopLeft, TopMiddle, TopRight, MiddleLeft, MiddleRight, BottomLeft, BottomMiddle, BottomRight)
    }
}
