package domain

data class Block(
    val type: BlockType = BlockType.GENERAL
) {
    var isOpened: Boolean = false
        private set
    var mineCount = type.defaultValue
        private set

    fun increaseMineCount() {
        if (type != BlockType.MINE) {
            mineCount++
        }
    }

    fun open(): Int {
        if (!isOpened) {
            isOpened = true
            return 1
        }
        return 0
    }

    fun isMine() = type == BlockType.MINE
}
