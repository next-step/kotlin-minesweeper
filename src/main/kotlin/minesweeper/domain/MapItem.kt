package minesweeper.domain

/**
 * ### 지도를 구성하는 아이템입니다. 지뢰와 빈칸으로 구분됩니다.
 */
sealed class MapItem {
    abstract val item: String
    var isOpened: Boolean = false
        private set
    fun open() {
        isOpened = true
    }
}

data class Mine(
    override val item: String = "MINE",
) : MapItem()

data class Empty(
    override val item: String = "EMPTY",
    val surroundingMineCount: Int = 0,
) : MapItem()
