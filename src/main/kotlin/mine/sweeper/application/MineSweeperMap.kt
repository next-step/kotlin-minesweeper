package mine.sweeper.application

import mine.sweeper.domain.Field
import mine.sweeper.domain.Fields
import mine.sweeper.domain.SafeField
import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.value.MineCount
import mine.sweeper.view.dto.Position

class MineSweeperMap(
    val fields: Fields,
    private val mineCount: MineCount
) {
    fun findUncheckOrNull(position: Position): Field? {
        val field = fields.findOrNull(position)
        if (field == null || field.checked) return null
        return field
    }

    fun open(position: Position, field: Field): GameStatus {
        val status = field.open()
        if (status === GameStatus.GAME_OVER) return GameStatus.GAME_OVER
        if (isEmptyFields(field)) SurroundFieldManager.openEmptyFields(position, fields, HashSet())
        if (isGameComplete()) return GameStatus.COMPLETE
        return status
    }

    private fun isGameComplete(): Boolean {
        return mineCount.value == fields.remainingFieldCount()
    }

    private fun isEmptyFields(
        field: Field
    ): Boolean {
        return (field as SafeField).isEmpty()
    }
}
