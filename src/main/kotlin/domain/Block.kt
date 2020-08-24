package domain

abstract class Block {
    var isOpened: Boolean = false
        private set

    abstract val isMine: Boolean
    abstract var mineCount: Int

    abstract fun increaseMineCount()

    fun open(): Boolean {
        if (!isOpened) {
            isOpened = true
            return true
        }
        return false
    }
}
