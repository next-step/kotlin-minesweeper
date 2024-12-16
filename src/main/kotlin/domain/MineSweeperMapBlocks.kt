package domain

class MineSweeperMapBlocks(private val _blocks: MutableList<MineSweeperMapBlock>) {
    val blocks: List<MineSweeperMapBlock>
        get() = _blocks.toList()

    fun isMine(index: Int): Boolean {
        return _blocks[index].isMine
    }

    fun setMine(index: Int) {
        if (!isMine(index)) {
            _blocks[index].setMine()
        }
    }

    fun getMineAroundCount(index: Int): Int {
        return _blocks.get(index).mineAroundCount
    }

    fun increaseMineAroundCount(index: Int) {
        require(index in 0 until _blocks.size)
        _blocks[index].increaseMineAroundCount()
    }

    fun isOpen(index: Int): Boolean {
        return _blocks[index].isOpen
    }

    fun open(index: Int) {
        _blocks[index].open()
    }

    fun isWin(): Boolean {
        return _blocks.stream().allMatch { it.isMine || it.isOpen }
    }
}
