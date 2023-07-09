package mine.sweeper

import mine.sweeper.domain.Field
import mine.sweeper.domain.MineField
import mine.sweeper.domain.Position
import mine.sweeper.domain.SafeField

object Fixture {
    fun middleMineFields(): List<Field> {
        return listOf(
            SafeField(Position(0, 0)),
            SafeField(Position(1, 0)),
            SafeField(Position(2, 0)),
            SafeField(Position(0, 1)),
            MineField(Position(1, 1)),
            SafeField(Position(2, 1)),
            SafeField(Position(0, 2)),
            SafeField(Position(1, 2)),
            SafeField(Position(2, 2))
        )
    }
}
