package minesweeper.request

data class MinesCreateRequest(
    val height: Int,
    val width: Int,
    val mineCapacity: Int
)
