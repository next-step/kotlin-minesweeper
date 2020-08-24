package domain

abstract class Block {
    var isOpened: Boolean = false
        private set

    abstract val isMine: Boolean
    abstract var mineCount: Int

    abstract fun increaseMineCount()

    fun open(): Int {
        if (!isOpened) {
            isOpened = true
            return 1
        }
        return 0
    }
}
