package minesweeper.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MineMapTest {

  @Test
  fun `높이 5 가로 3인 지도는 크기가 15이다`() {
    // given
    val height = 5
    val width = 3
    val numberOfMines = 7
    val mapInfo = MapInformation(height, width, numberOfMines)
    val expected = 15

    // when
    val resultMap = MineMap().createMap(mapInfo)

    // then
    Assertions.assertThat(resultMap.size).isEqualTo(expected)
  }

  @Test
  fun `높이 5 가로 3 지뢰가 7개 있는 지도에 지뢰 수는 7이다`() {
    // given
    val height = 5
    val width = 3
    val numberOfMines = 7
    val mapInfo = MapInformation(height, width, numberOfMines)
    val expected = 7

    // when
    val resultMap = MineMap().createMap(mapInfo).count { it == MapElement.MINE }

    // then
    Assertions.assertThat(resultMap).isEqualTo(expected)
  }

  @Test
  fun `높이 5 가로 3 지뢰가 7개 있는 지도를 가로 크기로 자르면 5개의 리스트가 만들어진다`() {
    // given
    val height = 5
    val width = 3
    val numberOfMines = 7
    val mapInfo = MapInformation(height, width, numberOfMines)
    val expected = 7

    // when
    val resultMap = MineMap().createMap(mapInfo).count { it == MapElement.MINE }

    // then
    Assertions.assertThat(resultMap).isEqualTo(expected)
  }
}