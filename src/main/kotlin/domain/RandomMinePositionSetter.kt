package domain

class RandomMinePositionSetter : MinePositionSetter {
    override fun setPosition(mineSweeperMap: MineSweeperMap) {
        var x = (0 until mineSweeperMap.width).random()
        var y = (0 until mineSweeperMap.height).random()
        while (mineSweeperMap.map[y][x].isMine) {
            x = (0 until mineSweeperMap.width).random()
            y = (0 until mineSweeperMap.height).random()
        }
        mineSweeperMap.map[y][x].isMine = true
    }
}
