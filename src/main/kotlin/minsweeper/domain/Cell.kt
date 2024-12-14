package minsweeper.domain

sealed interface Cell {
    data class Island(val aroundMineCount: Int) : Cell
    data object Mine : Cell
}
