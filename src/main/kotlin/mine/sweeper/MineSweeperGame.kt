package mine.sweeper

import mine.sweeper.domain.MapSize
import mine.sweeper.domain.value.MineCount
import mine.sweeper.field.domain.FieldsInitializer
import mine.sweeper.view.OutputView

class MineSweeperGame(mapSize: MapSize, mineCount: MineCount) {
    private val fields2 = FieldsInitializer(mapSize).createFields(mineCount)

    fun printEntireMap() {
        OutputView.printMap(fields2.fields)
    }
}
