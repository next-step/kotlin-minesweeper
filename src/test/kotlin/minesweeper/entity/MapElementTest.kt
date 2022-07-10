package minesweeper.entity

import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

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

    @Test
    fun `0 개의 지뢰는 ZERO에 대응된다`() {
        // given
        val countOfMines = 0
        val expected = MapElement.ZERO

        // when
        val result = MapElement.find(countOfMines)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `1 개의 지뢰는 ZERO에 대응된다`() {
        // given
        val countOfMines = 1
        val expected = MapElement.ONE

        // when
        val result = MapElement.find(countOfMines)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `2 개의 지뢰는 ZERO에 대응된다`() {
        // given
        val countOfMines = 2
        val expected = MapElement.TWO

        // when
        val result = MapElement.find(countOfMines)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `3 개의 지뢰는 ZERO에 대응된다`() {
        // given
        val countOfMines = 3
        val expected = MapElement.THREE

        // when
        val result = MapElement.find(countOfMines)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `4 개의 지뢰는 ZERO에 대응된다`() {
        // given
        val countOfMines = 4
        val expected = MapElement.FOUR

        // when
        val result = MapElement.find(countOfMines)

        // then
        Assertions.assertThat(result).isEqualTo(expected)
    }
}
