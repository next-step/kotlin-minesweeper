package model

object MapGenerator {

    fun generateMap(height: Int, width: Int, mineCount: Int): GameMap {
        val map = List(height) { List(width) { MinePoint(false) } }
        var leftMine = mineCount
        while (leftMine > 0) {
            val x = (Math.random() * height).toInt()
            val y = (Math.random() * width).toInt()
            if (!map[x][y].isMine) {
                map[x][y].isMine = true
                leftMine--
            }
        }
        return GameMap(map)
    }
}
