package tdd.minesweeper.ui.request

data class MineBoardCreateRequest(
    val width: Int,
    val height: Int,
    val mineCapacity: Int
)
