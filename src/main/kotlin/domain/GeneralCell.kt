package domain

class GeneralCell : Cell {
    override fun getValue(): String = "O"

    override fun isMine(): Boolean {
        return false
    }
}
