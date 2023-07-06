package mine.sweeper

import mine.sweeper.application.MapInitializer
import mine.sweeper.application.MineSweeperGame
import mine.sweeper.application.value.Height
import mine.sweeper.application.value.MineCount
import mine.sweeper.application.value.Width
import mine.sweeper.domain.Fields
import mine.sweeper.domain.Vulture
import mine.sweeper.view.dto.MapSize

object Fixture {
    fun createMineGame(
        height: Height = Height(5),
        width: Width = Width(5),
        mineCount: MineCount = MineCount(1)
    ): MineSweeperGame {
        val mapSize = MapSize(height, width)
        val vulture = Vulture(mapSize, mineCount)
        val mineSweeperMap = MapInitializer(mapSize).create(vulture.findMinesPosition())
        return MineSweeperGame(mineSweeperMap)
    }

    fun baseFields(
        height: Height = Height(5),
        width: Width = Width(5),
    ): Fields {
        val mapSize = MapSize(height, width)
        return Fields(mapSize)
    }
}
