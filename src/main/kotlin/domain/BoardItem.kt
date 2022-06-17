package domain

sealed class BoardItem {
    class Normal(private var nearMineCount: Int = 0) : BoardItem() {
        fun increaseCount() {
            nearMineCount++
        }

        fun getNearMineCount(): Int {
            return nearMineCount
        }
    }
    object Mine : BoardItem()
    object Opened : BoardItem()

    companion object {
        fun getItemType(isMine: Boolean): BoardItem {
            return when (isMine) {
                true -> Mine
                false -> Normal()
            }
        }
    }
}
