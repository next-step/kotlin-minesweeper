package minesweeper.domain.square

import minesweeper.domain.squarestate.Boundary
import minesweeper.domain.squarestate.Mine
import minesweeper.domain.squarestate.Empty
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach

class SquareTest {
    private lateinit var boundarySquare: Square
    private lateinit var mineSquare: Square
    private lateinit var emptySquare: Square

    @BeforeEach
    fun setUp() {
        boundarySquare = Square(4, 4, state = Boundary())
        mineSquare = Square(2, 2, state = Mine())
        emptySquare = Square(1, 1, state = Empty.default)
    }

    @Test
    fun `가장자리 위치인지 확인`() {
        // when
        val isOnBoundary = boundarySquare.isOnBoundary(3, 3)
        val isAlsoOnBoundary = emptySquare.isOnBoundary(3, 3)

        // then
        assertThat(isOnBoundary).isTrue()
        assertThat(isAlsoOnBoundary).isFalse()
    }

    @Test
    fun `주어진 위치와 같은 위치를 가지고 있는지 확인`() {
        // given
        val samePosition = Position(1, 1)
        val differentPosition = Position(1, 2)

        // when
        val isSame: Boolean = emptySquare.hasSamePosition(samePosition)
        val isAlsoSame: Boolean = emptySquare.hasSamePosition(differentPosition)

        // then
        assertThat(isSame).isTrue()
        assertThat(isAlsoSame).isFalse()
    }

    @Test
    fun `지뢰인지 확인`() {
        assertThat(boundarySquare.isMine()).isFalse()
        assertThat(mineSquare.isMine()).isTrue()
        assertThat(emptySquare.isMine()).isFalse()
    }

    @Test
    fun `가장자리인지 확인`() {
        assertThat(boundarySquare.isBoundary()).isTrue()
        assertThat(mineSquare.isBoundary()).isFalse()
        assertThat(emptySquare.isBoundary()).isFalse()
    }

    @Test
    fun `주변 8곳의 위치를 가져오는지 확인`() {
        // given
        val expected: List<Position> = listOf(
            Position(0, 1),
            Position(0, 0),
            Position(0, 2),
            Position(1, 0),
            Position(1, 2),
            Position(2, 1),
            Position(2, 0),
            Position(2, 2)
        )

        // when
        val actual: List<Position> = emptySquare.allAroundPositions()

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `지뢰 개수가 업데이트된 지점을 반환하는지 확인`() {
        // when
        val squareWith3Mines: Square = emptySquare.updateMineCount(3)

        // then
        assertThat(emptySquare.currentState()).isEqualTo("0")
        assertThat(squareWith3Mines.currentState()).isEqualTo("3")
    }
}
