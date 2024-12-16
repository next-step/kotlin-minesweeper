package domain

class MineSweeperMapBlocks(private val _blocks: MutableList<MineSweeperMapBlock>) {
    val blocks: List<MineSweeperMapBlock>
        get() = _blocks.toList()

    fun isMine(index: Int): Boolean {
        isOutOfBoard(index)
        return _blocks[index].isMine
    }

    fun setMine(index: Int) {
        isOutOfBoard(index)
        _blocks[index].setMine()
    }

    fun getMineAroundCount(index: Int): Int {
        isOutOfBoard(index)
        return _blocks.get(index).mineAroundCount
    }

    fun increaseMineAroundCount(index: Int) {
        isOutOfBoard(index)
        _blocks[index].increaseMineAroundCount()
    }

    fun isOpen(index: Int): Boolean {
        isOutOfBoard(index)
        return _blocks[index].isOpen
    }

    fun open(index: Int) {
        isOutOfBoard(index)
        _blocks[index].open()
    }

    fun isWin(): Boolean {
        return _blocks.stream().allMatch { it.isMine || it.isOpen }
    }

    fun isLose(): Boolean {
        return _blocks.stream().anyMatch { it.isMine && it.isOpen }
    }

    private fun isOutOfBoard(index: Int) {
        require(index in 0 until _blocks.size) { "보드 밖에 있어요" }
    }
}
