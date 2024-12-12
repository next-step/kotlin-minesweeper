package domain

class MineSweeperMap(
    val mineSweeperMapShape: MineSweeperMapShape,
    val blocks: MineSweeperMapBlocks,
) {
    fun isMine(
        row: Int,
        col: Int,
    ): Boolean {
        return blocks.isMine(mineSweeperMapShape.width * col + row)
    }

    fun setMine(
        row: Int,
        col: Int,
    ) {
        if (!isMine(row, col)) {
            val index = mineSweeperMapShape.width * col + row
            blocks.setMine(index)
            increaseBlocksMineAroundCount(index)
        }
    }

    private fun increaseBlocksMineAroundCount(index: Int) {
        if (index % getWidth() != getWidth() - 1) {
            blocks.increaseMineAroundCount(index + 1)
        }
        if (index % getWidth() != 0) {
            blocks.increaseMineAroundCount(index + -1)
        }
        if (index.div(getWidth()) != getHeight() - 1) {
            blocks.increaseMineAroundCount(index + getWidth())
        }
        if (index.div(getWidth()) != 0) {
            blocks.increaseMineAroundCount(index - getWidth())
        }
        if (index % getWidth() != getWidth() - 1 && index.div(getWidth()) != getHeight() - 1) {
            blocks.increaseMineAroundCount(index + getWidth() + 1)
        }
        if (index % getWidth() != getWidth() - 1 && index.div(getWidth()) != 0) {
            blocks.increaseMineAroundCount(index - getWidth() + 1)
        }
        if (index % getWidth() != 0 && index.div(getWidth()) != getHeight() - 1) {
            blocks.increaseMineAroundCount(index + getWidth() - 1)
        }
        if (index % getWidth() != 0 && index.div(getWidth()) != 0) {
            blocks.increaseMineAroundCount(index - getWidth() - 1)
        }
    }

    fun getWidth(): Int {
        return mineSweeperMapShape.width
    }

    fun getHeight(): Int {
        return mineSweeperMapShape.height
    }

    fun getMineAroundCount(index: Int): Int {
        return blocks.getMineAroundCount(index)
    }

    companion object {
        fun getDefaultMap(
            width: Int,
            height: Int,
        ): MineSweeperMap {
            val map = MineSweeperMapBlocks(MutableList(height.times(width)) { MineSweeperMapBlock() })
            return MineSweeperMap(MineSweeperMapShape(width, height), map)
        }
    }
}
