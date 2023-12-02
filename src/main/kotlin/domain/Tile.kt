package domain

enum class Tile(val alias: String) {
    Safe("C"),
    Mine("*");

    companion object {
        fun of(isMine: Boolean): Tile {
            if (isMine) {
                return Mine
            }

            return Safe
        }
    }
}
