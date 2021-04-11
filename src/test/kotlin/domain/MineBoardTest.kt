package domain

import domain.block.Block
import domain.block.Mine
import domain.block.Nothing
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertThrows

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

        val result = underTest.getSurroundingMineCountedBoard()
        assertThat(result).isEqualTo(expected)
    }

    @Test
    fun `존재하지 않는 좌표를 확인하는 경우 예외가 발생한다`() {
        val underTest = MineBoard(
            2, 2,
            mapOf(
                Coordinate(1, 1) to Mine(),
                Coordinate(2, 1) to Nothing(),
                Coordinate(1, 2) to Mine(),
                Coordinate(2, 2) to Nothing()
            )
        ).getSurroundingMineCountedBoard()
        val notExistCoordinate = Coordinate(10, 10)
        val expectedMessage = "해당 좌표가 존재하지 않습니다. 좌표: $notExistCoordinate, width: 2, height: 2"

        val result = assertThrows<IllegalArgumentException> { underTest.check(notExistCoordinate) }

        assertThat(result.message).isEqualTo(expectedMessage)
    }
}
