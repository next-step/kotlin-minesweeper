package domain

enum class ItemType(val display: String) {
    NORMAL("O"),
    MINE("X");

    companion object {
        fun getDisplay(isMine: Boolean): String {
            return if (isMine) MINE.display else NORMAL.display
        }
    }
}
