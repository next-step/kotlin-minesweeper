package mine.sweeper.application

import mine.sweeper.domain.Fields
import mine.sweeper.domain.SafeField
import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.value.MineCount
import mine.sweeper.domain.value.SurroundDirection
import mine.sweeper.view.dto.Position

class MineSweeperMap(
    val fields: Fields,
    val mineCount: MineCount
) {
    private val surroundOpener: SurroundOpener = SurroundOpener(fields)

    fun open(position: Position): GameStatus {
        val field = fields.get(position) ?: return GameStatus.ON_PROGRESS
        val gameStatus = field.open()
        if (field is SafeField && field.isEmpty) surroundOpener.open(position, HashSet())
        return gameStatus
    }

    private inner class SurroundOpener(private val fields: Fields) {
        fun open(position: Position, visited: HashSet<Position>) {
            for (surround in SurroundDirection.values()) {
                val target = Position(position.x + surround.x, position.y + surround.y)
                if (visited.contains(target)) continue
                val safeField = openSafeField(target, fields, visited)
                safeField?.let {
                    if (it.isEmpty) open(target, visited)
                }
            }
        }

        private fun openSafeField(target: Position, fields: Fields, visited: HashSet<Position>): SafeField? {
            val safeField = fields.get(target) as? SafeField ?: return null
            visited.add(target)
            safeField.open()
            return safeField
        }
    }
}
