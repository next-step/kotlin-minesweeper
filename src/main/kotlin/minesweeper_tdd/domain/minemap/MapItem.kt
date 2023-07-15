package minesweeper_tdd.domain.minemap

/**
 * ### 지뢰찾기 지도를 구성하는 아이템입니다. 지뢰와 빈칸으로 구분됩니다.
 */
sealed class MapItem {
    abstract val item: String
    var isOpened: Boolean = false
        private set
    fun open() {
        isOpened = true
    }
}

/**
 * ### 지뢰를 표현합니다.
 */
data class Mine(
    override val item: String = "MINE",
) : MapItem()

/**
 * ### 지뢰가 아닌 빈칸을 표현합니다.
 */
data class Empty(
    override val item: String = "EMPTY",
    val surroundingMineCount: Int = 0,
) : MapItem()
