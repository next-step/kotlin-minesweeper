package domain

class SafetyBlock : Block {
    override var symbol: String = "0"
        private set

    override fun isMine(): Boolean {
        return this.symbol === "*"
    }

    override fun setMineCount() {
        this.symbol = (symbol.toInt() + 1).toString()
    }

    override fun toString(): String {
        return symbol
    }
}
