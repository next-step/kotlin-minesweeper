package domain

class MineCell : Cell {

    override fun getValue(): String = "X"

    override fun isMine(): Boolean {
        return true
    }
}
