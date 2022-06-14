package domain

enum class BoardItem(val display: String) {
    NORMAL("O"),
    MINE("X");

    companion object {
        fun getItemType(isMine: Boolean): BoardItem {
            return when(isMine) {
                true -> MINE
                false -> NORMAL
            }
        }
    }
}
