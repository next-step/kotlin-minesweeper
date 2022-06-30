class NonMine(private val aroundMineCount: Int = INIT_MINE_COUNT): Square {
    override fun isMine(): Boolean = false

    override fun display(): String = "C"

    override fun countAroundMine(): Int = aroundMineCount

    companion object {
        private const val INIT_MINE_COUNT = -1
    }
}
