data class Cell(val value: MineStatus) {
    val isMine: Boolean
        get() = value == MineStatus.MINE

    fun plantMine(): Cell {
        return Cell(MineStatus.MINE)
    }
}
