package minesweeper.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MineCountingTest {

    @Test
    fun `row 값이 지도를 벗어나 탐색하면 isValid는 false를 리턴한다`() {
        // given
        val mapInformation = MapInformation(3, 3, 1)
        val firstRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val secondRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val thirdRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val minedMap = listOf<List<MapElement>>(firstRow, secondRow, thirdRow)

        // when
        val result = MineCounting(minedMap, mapInformation).isValid(4, 2)

        // then
        Assertions.assertThat(result).isFalse()
    }

    @Test
    fun `col 값이 지도를 벗어나 탐색하면 isValid는 false를 리턴한다`() {
        // given
        val mapInformation = MapInformation(3, 3, 1)
        val firstRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val secondRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val thirdRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val minedMap = listOf<List<MapElement>>(firstRow, secondRow, thirdRow)

        // when
        val result = MineCounting(minedMap, mapInformation).isValid(1, 3)

        // then
        Assertions.assertThat(result).isFalse()
    }

    @Test
    fun `지도 내부를 탐색하면 isValid는 true를 리턴한다`() {
        // given
        val mapInformation = MapInformation(3, 3, 1)
        val firstRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val secondRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val thirdRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val minedMap = listOf<List<MapElement>>(firstRow, secondRow, thirdRow)

        // when
        val result = MineCounting(minedMap, mapInformation).isValid(0, 2)

        // then
        Assertions.assertThat(result).isTrue()
    }

    @Test
    fun `상하좌우에 지뢰가 하나 있으면 MapElement ONE를 리턴한다`() {
        // given
        val mapInformation = MapInformation(3, 3, 1)
        val firstRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val secondRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val thirdRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val minedMap = listOf<List<MapElement>>(firstRow, secondRow, thirdRow)
        val expected = MapElement.ONE

        // when
        val result = MineCounting(minedMap, mapInformation).searchAllDirection(1, 1)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `상하좌우에 지뢰가 두 개 있으면 MapElement TWO를 리턴한다`() {
        // given
        val mapInformation = MapInformation(3, 3, 2)
        val firstRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val secondRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.MINE)
        val thirdRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val minedMap = listOf<List<MapElement>>(firstRow, secondRow, thirdRow)
        val expected = MapElement.TWO

        // when
        val result = MineCounting(minedMap, mapInformation).searchAllDirection(1, 1)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }

    fun `상하좌우에 지뢰가 세 개 있으면 MapElement THREE를 리턴한다`() {
        // given
        val mapInformation = MapInformation(3, 3, 3)
        val firstRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val secondRow = listOf<MapElement>(MapElement.MINE, MapElement.GROUND, MapElement.MINE)
        val thirdRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.GROUND)
        val minedMap = listOf<List<MapElement>>(firstRow, secondRow, thirdRow)
        val expected = MapElement.THREE

        // when
        val result = MineCounting(minedMap, mapInformation).searchAllDirection(1, 1)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }


    fun `네 방향에 지뢰가 네 개 있으면 MapElement FOUR를 리턴한다`() {
        // given
        val mapInformation = MapInformation(3, 3, 4)
        val firstRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val secondRow = listOf<MapElement>(MapElement.MINE, MapElement.GROUND, MapElement.MINE)
        val thirdRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val minedMap = listOf<List<MapElement>>(firstRow, secondRow, thirdRow)
        val expected = MapElement.FOUR

        // when
        val result = MineCounting(minedMap, mapInformation).searchAllDirection(1, 1)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `지뢰의 숫자를 나타내는 지도를 리턴한다`() {
        // given
        val mapInformation = MapInformation(3, 3, 3)
        val firstRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val secondRow = listOf<MapElement>(MapElement.GROUND, MapElement.GROUND, MapElement.MINE)
        val thirdRow = listOf<MapElement>(MapElement.GROUND, MapElement.MINE, MapElement.GROUND)
        val minedMap = listOf<List<MapElement>>(firstRow, secondRow, thirdRow)

        val firstExpectedRow = listOf<MapElement>(MapElement.ONE, MapElement.MINE, MapElement.TWO)
        val secondExpectedRow = listOf<MapElement>(MapElement.ZERO, MapElement.THREE, MapElement.MINE)
        val thirdExpectedRow = listOf<MapElement>(MapElement.ONE, MapElement.MINE, MapElement.TWO)
        val expectedMinedMap = listOf<List<MapElement>>(firstExpectedRow, secondExpectedRow, thirdExpectedRow)

        // when
        val result = MineCounting(minedMap, mapInformation).searchMap()

        // then
        Assertions.assertThat(result).isEqualTo(expectedMinedMap)
    }
}