data class Cell(val value: MineStatus) {
    fun isMine(): Boolean = value == MineStatus.MINE
}
