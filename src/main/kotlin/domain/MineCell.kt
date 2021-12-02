package domain

class MineCell(
    aroundMineCount: Int = 0
) : Cell {

    override fun getValue(): String = "X"

    override fun isMine(): Boolean {
        return true
    }
}
