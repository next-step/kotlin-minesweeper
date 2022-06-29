package minesweeper.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MapInformationTest {

  @Test
  fun `높이 5 가로 3 지뢰 7개인 지도에서 높이는 5이다`() {
    // given
    val height = 5
    val width = 3
    val numberOfMines = 7
    val mapInfo = MapInformation(height, width, numberOfMines)
    val expected = 5

    // then
    Assertions.assertThat(mapInfo.height).isEqualTo(expected)
  }

  @Test
  fun `높이 5 가로 3 지뢰 7개인 지도에서 가로는 3이다`() {
    // given
    val height = 5
    val width = 3
    val numberOfMines = 7
    val mapInfo = MapInformation(height, width, numberOfMines)
    val expected = 3

    // then
    Assertions.assertThat(mapInfo.width).isEqualTo(expected)
  }

  @Test
  fun `높이 5 가로 3 지뢰 7개인 지도에서 지뢰는 7개이다`() {
    // given
    val height = 5
    val width = 3
    val numberOfMines = 7
    val mapInfo = MapInformation(height, width, numberOfMines)
    val expected = 7

    // then
    Assertions.assertThat(mapInfo.numberOfMines).isEqualTo(expected)
  }
}