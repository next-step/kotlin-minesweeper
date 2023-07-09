package mine.sweeper.domain

import mine.sweeper.domain.value.GameStatus
import mine.sweeper.domain.value.Position
import mine.sweeper.domain.value.SurroundDirection

class Fields(val fieldList: List<Field>) {
    private val surroundOpener: SurroundOpener = SurroundOpener(HashSet())

    val size = fieldList.size

    val mineCount = fieldList.filterIsInstance<MineField>().count()

    val remainingFieldCount: Int
        get() = fieldList.size - fieldList.count { it.checked }

    val sortedList: List<Field>
        get() = fieldList.sortedWith(compareBy({ it.position.x }, { it.position.y }))

    private fun findOrNull(position: Position): Field? {
        return fieldList.find { it.isSame(position) }
    }

    fun open(position: Position): GameStatus {
        val field = findOrNull(position) ?: return GameStatus.ON_PROGRESS
        val gameStatus = field.open()
        if (field is SafeField && field.isEmpty) surroundOpener.open(position)
        return gameStatus
    }

    private inner class SurroundOpener(private val visited: HashSet<Position>) {
        fun open(position: Position) {
            for (surround in SurroundDirection.values()) {
                val target = Position(position.x + surround.x, position.y + surround.y)
                if (target in visited) continue
                val safeField = openSafeField(target, visited) ?: continue
                if (safeField.isEmpty) open(target)
            }
        }

        private fun openSafeField(target: Position, visited: HashSet<Position>): SafeField? {
            val safeField = findOrNull(target) as? SafeField ?: return null
            visited.add(target)
            safeField.open()
            return safeField
        }
    }
}
