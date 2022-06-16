package domain

sealed class BoardItem(private var nearMineCount: Int = 0) {
    class Normal : BoardItem()
    object Mine : BoardItem()

    fun increaseCount() {
        nearMineCount++
    }

    fun getNearMineCount(): Int {
        return nearMineCount
    }

    companion object {
        fun getItemType(isMine: Boolean): BoardItem {
            return when (isMine) {
                true -> Mine
                false -> Normal()
            }
        }
    }
}
