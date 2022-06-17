package domain

sealed class BoardItem(var nearMineCount: Int = 0, var opened: Boolean = false) {
    class Normal : BoardItem()
    object Mine : BoardItem()

    fun increaseCount() {
        nearMineCount++
    }

    companion object {
        fun getItemType(isMine: Boolean, closed: Boolean): BoardItem {
            return when (isMine) {
                true -> Mine
                false -> Normal()
            }
        }
    }
}
