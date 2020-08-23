package domain

class Mine : Block {
    override val symbol = "*"

    override fun toString(): String {
        return symbol
    }
}
