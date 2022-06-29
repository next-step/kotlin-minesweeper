package minesweeper.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*

internal class MapElementTest {
  @Test
  fun `일반 땅(GROUND)은 C로 표현된다`() {
    // given
    val groundRepresent = MapElement.GROUND.represent
    val expected = "C"

    // then
    Assertions.assertThat(groundRepresent).isEqualTo(expected)
  }

  @Test
  fun `지뢰(MINE)은 *로 표현된다`() {
    // given
    val groundRepresent = MapElement.MINE.represent
    val expected = "*"

    // then
    Assertions.assertThat(groundRepresent).isEqualTo(expected)
  }
}