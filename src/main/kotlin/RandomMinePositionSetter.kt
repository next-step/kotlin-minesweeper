import domain.MinePositionSetter
import domain.MineSweeperMap

class RandomMinePositionSetter : MinePositionSetter {
    override fun setPosition(mineSweeperMap: MineSweeperMap) {
        var x = (0 until mineSweeperMap.getWidth()).random()
        var y = (0 until mineSweeperMap.getHeight()).random()
        while (mineSweeperMap.isMine(x, y)) {
            x = (0 until mineSweeperMap.getWidth()).random()
            y = (0 until mineSweeperMap.getHeight()).random()
        }
        mineSweeperMap.setMine(x, y)
    }
}
