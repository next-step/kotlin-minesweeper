package minesweeper

import io.kotest.matchers.shouldBe
import minesweeper.domain.MineMap
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import java.lang.IllegalArgumentException

internal class MineMapTest {

    @ParameterizedTest
    @CsvSource(
        "0, 1, 1",
        "1, 0, 1",
        "1, 1, 0",
    )
    internal fun `높이와 너비 그리고 지뢰 개수가 0보다 작으면 예외가 발생한다`(
        height: Int,
        width: Int,
        mineCount: Int,
    ) {
        assertThrows<IllegalArgumentException> { MineMap(height, width, mineCount) }
    }

    @ParameterizedTest
    @CsvSource(
        "3, 4, 10, 0, 12, 10, 2",
        "10, 10, 10, 0, 100, 10, 90"
    )
    internal fun `지뢰를 매설하면 지뢰 개수 만큼 지도에 지뢰가 추가된다`(
        height: Int,
        width: Int,
        mineCount: Int,
        beforePlantMineCount: Int,
        beforePlantEmptyCount: Int,
        afterPlantMineCount: Int,
        afterPlantEmptyCount: Int,
    ) {
        val sut = MineMap(
            height = height,
            width = width,
            mineCount = mineCount
        )
        sut.toString().count { it == MineMap.MINE_SYMBOL.first() } shouldBe beforePlantMineCount
        sut.toString().count { it == MineMap.EMPTY_SYMBOL.first() } shouldBe beforePlantEmptyCount

        sut.plantMine()

        sut.toString().count { it == MineMap.MINE_SYMBOL.first() } shouldBe afterPlantMineCount
        sut.toString().count { it == MineMap.EMPTY_SYMBOL.first() } shouldBe afterPlantEmptyCount
    }
}
