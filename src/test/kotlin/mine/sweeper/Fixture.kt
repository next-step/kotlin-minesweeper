package mine.sweeper

import mine.sweeper.application.FieldsManager
import mine.sweeper.application.MineSweeperGame
import mine.sweeper.application.value.Height
import mine.sweeper.application.value.Width
import mine.sweeper.domain.SafeField
import mine.sweeper.domain.Vulture
import mine.sweeper.view.dto.MapSize
import mine.sweeper.view.dto.Position

class Fixture {
    companion object {
        fun MINE_SWEEPER_GAME(height: Height = Height(5), width: Width = Width(5)): MineSweeperGame {
            val mapSize = MapSize(height, width)
            val fieldsManager = FieldsManager(mapSize)
            val vulture = Vulture(mapSize)
            return MineSweeperGame(fieldsManager, vulture)
        }
        val TWO_TWO_FIELDS = listOf(
            SafeField(Position(0, 0)),
            SafeField(Position(1, 0)),
            SafeField(Position(0, 1)),
            SafeField(Position(1, 1)),
        )
    }
}
