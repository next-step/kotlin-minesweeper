package domain

data class BoardInfo(
    val layout: Layout
) {
    operator fun get(y: Int): Row {
        return layout[y]
    }
}
