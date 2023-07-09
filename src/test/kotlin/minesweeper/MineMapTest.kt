package minesweeper

import io.kotest.matchers.shouldBe
import minesweeper.domain.Empty
import minesweeper.domain.Mine
import minesweeper.domain.MineMap
import minesweeper.domain.MineMapConfig
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MineMapTest {

    @ParameterizedTest
    @CsvSource(
        "3, 4, 10, 10, 2",
        "10, 10, 10, 10, 90"
    )
    internal fun `지뢰를 매설하면 지뢰 개수 만큼 지도에 지뢰가 추가된다`(
        height: Int,
        width: Int,
        mineCount: Int,
        afterPlantMineCount: Int,
        afterPlantEmptyCount: Int,
    ) {
        val sut = MineMap(
            MineMapConfig(
                height = height,
                width = width,
                mineCount = mineCount,
            )
        )
        val mapAfterPlantMine = sut.getCurrentMap()
        mapAfterPlantMine.values.filterIsInstance<Mine>().size shouldBe afterPlantMineCount
        mapAfterPlantMine.values.filterIsInstance<Empty>().size shouldBe afterPlantEmptyCount
    }
}
