package mine.sweeper

import mine.sweeper.application.MapInitializer
import mine.sweeper.application.MineSweeperGame
import mine.sweeper.application.MineSweeperMap
import mine.sweeper.application.RandomPositionManager
import mine.sweeper.domain.Vulture
import mine.sweeper.domain.value.Height
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.Width
import mine.sweeper.view.dto.MapSize

object Fixture {
    fun createMineGame(
        height: Height = Height(5),
        width: Width = Width(5),
        mineCount: MineCount = MineCount(1)
    ): MineSweeperGame {
        val mapSize = MapSize(height, width)
        val vulture = Vulture(RandomPositionManager(mapSize), mineCount)
        val mineSweeperMap = MapInitializer(mapSize, vulture.newMinePositions).create()
        return MineSweeperGame(mineSweeperMap)
    }

    fun createMineMap(
        height: Height = Height(5),
        width: Width = Width(5),
        mineCount: MineCount = MineCount(1)
    ): MineSweeperMap {
        val mapSize = MapSize(height, width)
        val vulture = Vulture(RandomPositionManager(mapSize), mineCount)
        return MapInitializer(mapSize, vulture.newMinePositions).create()
    }
}
