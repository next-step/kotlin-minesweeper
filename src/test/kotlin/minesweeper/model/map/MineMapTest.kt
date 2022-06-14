package minesweeper.model.map

import minesweeper.model.map.coordinate.MapSize
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.assertAll
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource

internal class MineMapTest {

    @ParameterizedTest
    @CsvSource(
        "1,1,1", // width, height, mineCount
        "1,2,2",
        "10,10,10",
        "5,5,5"
    )
    fun `높이 x 너비를 갖는 맵 생성 테스트`(width: Int, height: Int, mineCount: Int) {

        val actualMap = MineMap.build(MapSize(width, height)) { position ->
            val index = position.row * width + position.column
            index < mineCount
        }

        val expectedCellCount = width * height
        val expectedMineCount = mineCount
        val expectedSafeCellCount = expectedCellCount - mineCount

        assertAll(
            { assertThat(actualMap.cellCount).isEqualTo(expectedCellCount) },
            { assertThat(actualMap.mineCount).isEqualTo(expectedMineCount) },
            { assertThat(actualMap.safeCellCount).isEqualTo(expectedSafeCellCount) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "0,100",
        "100,0",
        "0,0"
    )
    fun `맵 크기가 0 이면 에러`(width: Int, height: Int) {
        assertThrows<IllegalArgumentException> { MineMap.build(MapSize(width, height)) { true } }
    }
}
