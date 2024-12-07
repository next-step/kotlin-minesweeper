package minesweeper.domain.point

data class Mine(
    val r: Int,
    val c: Int,
) : Point(r, c) {
    override fun isMine(): Boolean = true
}
