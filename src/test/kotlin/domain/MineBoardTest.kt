package domain

import domain.block.Block
import domain.block.Mine
import domain.block.Nothing
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

internal class MineBoardTest {

    @Test
    fun `MineBoard 생성한다`() {
        val map = HashMap<Coordinate, Block>()
        map[Coordinate(1, 1)] = Nothing()

        val result = MineBoard(1, 1, map)

        assertThat(result).isNotNull
    }

    /**
     * ■ □ ■  =>   ■ 3 ■
     * ■ □ □       ■ 3 1
     * □ □ □       1 1 0
     */
    @Test
    fun `지뢰찾기판에 주변지뢰들을 확인한다'`() {
        val underTest = MineBoard(
            3, 3,
            mapOf(
                Coordinate(1, 1) to Mine(),
                Coordinate(2, 1) to Nothing(),
                Coordinate(3, 1) to Mine(),
                Coordinate(1, 2) to Mine(),
                Coordinate(2, 2) to Nothing(),
                Coordinate(3, 2) to Nothing(),
                Coordinate(1, 3) to Nothing(),
                Coordinate(2, 3) to Nothing(),
                Coordinate(3, 3) to Nothing()
            )
        )

        val expected = MineBoard(
            3, 3,
            mapOf(
                Coordinate(1, 1) to Mine(),
                Coordinate(2, 1) to Nothing(3),
                Coordinate(3, 1) to Mine(),
                Coordinate(1, 2) to Mine(),
                Coordinate(2, 2) to Nothing(3),
                Coordinate(3, 2) to Nothing(1),
                Coordinate(1, 3) to Nothing(1),
                Coordinate(2, 3) to Nothing(1),
                Coordinate(3, 3) to Nothing(0)
            )
        )

        val result = underTest.surroundingMineCountedBoard()
        assertThat(result).isEqualTo(expected)
    }
}
