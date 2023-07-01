package domain

enum class Cell(
    val symbol: String,
) {
    CLOSED("C"),
    MINE("*"),
    ;

    companion object {
        fun of(isMine: Boolean): Cell {
            return if (isMine) MINE else CLOSED
        }
    }
}
