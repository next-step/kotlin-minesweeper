package domain

enum class Block {
    MINE, NOTHING;

    companion object {
        fun from(isMine: Boolean): Block {
            return if (isMine) MINE else NOTHING
        }
    }
}
