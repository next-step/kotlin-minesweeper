package domain

sealed class BoardItem() {
    data class Normal(var nearMineCount: Int = 0) : BoardItem() {
        private var opened = false

        fun increaseCount() {
            nearMineCount++
        }

        fun open() {
            opened = true
        }

        fun isOpened(): Boolean {
            return opened
        }
    }

    object Mine : BoardItem()


    companion object {
        fun getItemType(isMine: Boolean): BoardItem {
            return when (isMine) {
                true -> Mine
                false -> Normal()
            }
        }
    }
}
