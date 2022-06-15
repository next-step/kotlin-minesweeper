package domain

sealed class BoardItem(private var nearMineCount: Int) {
    class Normal : BoardItem(0)
    class Mine : BoardItem(0)

    fun increaseCount() {
        nearMineCount++
    }

    fun getNearMineCount(): Int {
        return nearMineCount
    }

    companion object {
        fun getItemType(isMine: Boolean): BoardItem {
            return when (isMine) {
                true -> Mine()
                false -> Normal()
            }
        }
    }
}
