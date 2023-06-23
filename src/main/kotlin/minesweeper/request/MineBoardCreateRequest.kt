package minesweeper.request

data class MineBoardCreateRequest(
    val height: Int,
    val width: Int,
    val mineCapacity: Int
)
