package domain

class Mine : Block {
    override val symbol = "*"

    override fun isMine(): Boolean {
        return this.symbol === "*"
    }

    override fun setMineCount() {
    }

    override fun toString(): String {
        return symbol
    }
}
