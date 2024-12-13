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
        val x = index % getWidth()
        val y = index / getWidth()

        Directions.entries.stream().forEach { direction ->
            addMineCountAroundBlock(direction, x, y)
        }
    }

    private fun addMineCountAroundBlock(
        direction: Directions,
        x: Int,
        y: Int,
    ) {
        val nx = x + direction.horizontalDirection
        val ny = y + direction.verticalDirection
        if (nx >= 0 && ny >= 0 && nx < getWidth() && ny < getHeight()) {
            blocks.increaseMineAroundCount(ny * getWidth() + nx)
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
