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
    fun `높이 x 너비를 갖는 맵 생성 테스트`(width: Int, height: Int, expectedMineCount: Int) {

        // given
        val mapSize = MapSize(width, height)

        // when
        val actualMap = MineMap.build(mapSize) { position -> mapSize.indexOf(position) < expectedMineCount }
        val expectedCellCount = width * height
        val expectedSafeCellCount = expectedCellCount - expectedMineCount

        // then
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

    @ParameterizedTest
    @CsvSource(
        "1,1,1", // width, height, mineCount
        "1,2,2",
        "10,10,10",
        "5,5,5"
    )
    fun `랜던 맵 생성 테스트`(width: Int, height: Int, expectedMineCount: Int) {
        // given
        val mapSize = MapSize(width, height)

        // when
        val actualMap = MineMap.randomMap(mapSize, expectedMineCount)
        val expectedCellCount = width * height
        val expectedSafeCellCount = expectedCellCount - expectedMineCount

        // then
        assertAll(
            { assertThat(actualMap.cellCount).isEqualTo(expectedCellCount) },
            { assertThat(actualMap.mineCount).isEqualTo(expectedMineCount) },
            { assertThat(actualMap.safeCellCount).isEqualTo(expectedSafeCellCount) }
        )
    }

    @ParameterizedTest
    @CsvSource(
        "1,1,0", // width, height, mineCount
        "1,2,-1",
        "10,10,101",
        "5,5,1000"
    )
    fun `랜던 맵 생성 지뢰 갯수 범위 테스트`(width: Int, height: Int, expectedMineCount: Int) {
        // given
        val mapSize = MapSize(width, height)
        assertThrows<IllegalArgumentException> { MineMap.randomMap(mapSize, expectedMineCount) }
    }
}
