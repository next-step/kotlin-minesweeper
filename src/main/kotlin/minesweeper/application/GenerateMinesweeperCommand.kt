package minesweeper.application

data class GenerateMinesweeperCommand(
    val height: Int,
    val width: Int,
    val mineCount: Int,
) {
    val area: Int get() = height * width

    init {
        require(height > 0) { "높이는 0 보다 커야 합니다." }
        require(width > 0) { "너비는 0 보다 커야 합니다." }
        require(mineCount in 0..area) { "지뢰 개수는 0 이상이고 총 칸 개수 이하여야 합니다." }
    }
}
