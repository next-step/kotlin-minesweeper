package minesweeper.domain.cell

class NumberCell(
    private val aroundMineCount: Int,
    position: Position
) : Cell(position) {
    init {
        require(aroundMineCount in 0..8) { "폭탄의 갯수는 0..8 이여야 한다." }
    }

    override fun isMine() = false

    override fun toString() = NumberEmoji.getEmoji(aroundMineCount).toString()
}

enum class NumberEmoji(private val number: Int, private val emoji: String) {
    ZERO(0, "0️⃣"),
    ONE(1, "1️⃣"),
    TWO(2, "2️⃣"),
    THREE(3, "3️⃣"),
    FOUR(4, "4️⃣"),
    FIVE(5, "5️⃣"),
    SIX(6, "6️⃣"),
    SEVEN(7, "7️⃣"),
    EIGHT(8, "8️⃣");

    override fun toString() = emoji

    companion object {
        fun getEmoji(number: Int) = values().find { it.number == number } ?: ZERO
    }
}
