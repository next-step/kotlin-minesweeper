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
            blocks.setMine(mineSweeperMapShape.width * col + row)
        }
    }

    fun getWidth(): Int {
        return mineSweeperMapShape.width
    }

    fun getHeight(): Int {
        return mineSweeperMapShape.height
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
