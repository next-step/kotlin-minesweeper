package minesweeper.domain.mine

enum class MineSweeperShape(val shape: String) {
    MINE("*"), NUMBER("0");

    companion object {
        fun isMine(input: String): Boolean {
            return input == MINE.shape
        }
    }
}
