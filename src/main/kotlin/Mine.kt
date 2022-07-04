class Mine : Square {
    override fun isMine(): Boolean = true

    override fun display(): String = "C"

    override fun countAroundMine(): Int = INIT_MINE_COUNT

    companion object {
        private const val INIT_MINE_COUNT = -1
    }
}
