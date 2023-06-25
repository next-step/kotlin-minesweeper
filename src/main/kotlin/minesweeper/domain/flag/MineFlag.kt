package minesweeper.domain.flag

class MineFlag : Flag() {

    private val blockName: String = MINE_NAME

    override fun getCurrentState(): String = blockName

    companion object {
        private const val MINE_NAME = "*"
    }
}
