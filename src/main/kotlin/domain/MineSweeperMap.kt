package domain

class MineSweeperMap(val width: Int, val height: Int, var map: Array<Array<MineSweeperMapBlock>>) {
    companion object {
        fun getDefaultMap(
            width: Int,
            height: Int,
        ): MineSweeperMap {
            val map = Array(height) { Array(width) { MineSweeperMapBlock(false) } }
            return MineSweeperMap(width, height, map)
        }
    }
}
