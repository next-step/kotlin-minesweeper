package domain

import domain.block.Mine
import domain.block.Nothing
import org.assertj.core.api.Assertions
import org.junit.jupiter.api.Test

internal class MineBoardOpenedTest {

    @Test
    fun `지뢰가 아닌 블럭 중 더이상 오픈할 블럭이 없는 경우 True를 반환한다`() {
        // given
        val underTest = MineBoard(
            1, 3,
            mapOf(
                Coordinate(1, 1) to Nothing(),
                Coordinate(1, 2) to Mine(),
                Coordinate(1, 3) to Nothing()
            )
        )

        underTest.open(Coordinate(1, 1))
        underTest.open(Coordinate(1, 3))

        // when
        val result = underTest.notExistsToOpen()

        // then        
        Assertions.assertThat(result).isTrue()
    }

    @Test
    fun `지뢰가 아닌 블럭 중 더이상 오픈할 블럭이 있는 경우 False를 반환한다`() {
        // given
        val underTest = MineBoard(
            1, 3,
            mapOf(
                Coordinate(1, 1) to Nothing(),
                Coordinate(1, 2) to Mine(),
                Coordinate(1, 3) to Nothing()
            )
        )

        underTest.open(Coordinate(1, 1))

        // when
        val result = underTest.notExistsToOpen()

        // then        
        Assertions.assertThat(result).isFalse()
    }

    @Test
    fun `지뢰 중에서 오픈한 지뢰가 있는 경우 True를 반환한다`() {
        // given
        val underTest = MineBoard(
            1, 3,
            mapOf(
                Coordinate(1, 1) to Nothing(),
                Coordinate(1, 2) to Mine(),
                Coordinate(1, 3) to Nothing()
            )
        )

        underTest.open(Coordinate(1, 2))

        // when
        val result = underTest.existsOpenedMine()

        // then        
        Assertions.assertThat(result).isTrue()
    }

    @Test
    fun `지뢰 중에서 오픈한 지뢰가 없는 경우 False를 반환한다`() {
        // given
        val underTest = MineBoard(
            1, 3,
            mapOf(
                Coordinate(1, 1) to Nothing(),
                Coordinate(1, 2) to Mine(),
                Coordinate(1, 3) to Nothing()
            )
        )

        underTest.open(Coordinate(1, 1))

        // when
        val result = underTest.existsOpenedMine()

        // then        
        Assertions.assertThat(result).isFalse()
    }
}
