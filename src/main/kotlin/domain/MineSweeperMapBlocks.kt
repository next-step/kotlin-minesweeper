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
}
