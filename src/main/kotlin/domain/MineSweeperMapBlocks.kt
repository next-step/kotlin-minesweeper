package domain

class MineSweeperMapBlocks(val _blocks: MutableList<MineSweeperMapBlock>) {
    val blocks: List<MineSweeperMapBlock>
        get() = _blocks.toList()

    fun isMine(index: Int): Boolean {
        return _blocks[index].isMine
    }

    fun setMine(index: Int) {
        if (!isMine(index)) {
            _blocks[index].setMine(true)
        }
    }

    fun getMineAroundCount(index: Int): Int {
        return _blocks.get(index).mineAroundCount
    }

    fun increaseMineAroundCount(index: Int) {
        require(index in 0 until _blocks.size)
        _blocks[index].increaseMineAroundCount()
    }
}
