package mine.sweeper.field.domain

import mine.sweeper.domain.MapSize
import mine.sweeper.domain.Vulture
import mine.sweeper.domain.value.MineCount

class FieldsInitializer(mapSize: MapSize) {

    private val fieldsManager: FieldsManager = FieldsManager(mapSize)
    private val vulture: Vulture = Vulture(mapSize)

    fun createFields(mineCount: MineCount): Fields {
        val minePositions = vulture.findMinesPosition(mineCount)
        minePositions.forEach {
            fieldsManager.changeMineField(it)
        }
        return fieldsManager.toFields()
    }
}
