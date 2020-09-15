package minesweeper.domain.squares

import minesweeper.domain.square.Square
import minesweeper.domain.squarestate.Empty
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatExceptionOfType
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.BeforeEach
import java.lang.IllegalArgumentException

class SquaresTest {
    private lateinit var twoByTwoSquares: Squares

    @BeforeEach
    fun setUp() {
        twoByTwoSquares = Squares.createAllWithBoundary(2, 2)
            .updateStateIfOnBoundary(2, 2)
    }

    @Test
    fun `원하는 위치의 지점을 가져오는지 확인`() {
        // when
        val square: Square = twoByTwoSquares[1, 1]

        // then
        assertThat(square).isEqualTo(Square(1, 1, state = Empty.default))
    }

    @Test
    fun `위치에 맞는 지점이 없으면 에러를 발생시킨다`() {
        assertThatExceptionOfType(IllegalArgumentException::class.java)
            .isThrownBy { twoByTwoSquares[5, 5] }
            .withMessage("Invalid Position: (5, 5)")
    }

    @Test
    fun `가장자리일 때 상태가 Boundary로 업데이트되는지 확인`() {
        // when
        val zeroBoundarySquare: Square = twoByTwoSquares[0, 0]
        val maxBoundarySquare: Square = twoByTwoSquares[3, 3]
        val insideSquare: Square = twoByTwoSquares[1, 1]

        // then
        assertThat(zeroBoundarySquare.isBoundary()).isTrue()
        assertThat(maxBoundarySquare.isBoundary()).isTrue()
        assertThat(insideSquare.isBoundary()).isFalse()
    }

    @Test
    fun `원하는 순서로 지점 목록을 반환하는지 확인`() {
        // given
        val customSquares =
            Squares.createFrom(
                Square(2, 2),
                Square(3, 3),
                Square(1, 1)
            )

        val expected =
            Squares.createFrom(
                Square(1, 1),
                Square(2, 2),
                Square(3, 3)
            )

        // when
        val actual: Squares = customSquares.shuffled(object : SquaresShuffleStrategy {
            override fun shuffle(squares: List<Square>): List<Square> = squares.sortedBy { it.position.x }
        })

        // then
        assertThat(actual).isEqualTo(expected)
    }

    @Test
    fun `지뢰를 설치한다 (Boundary에는 지뢰를 설치할 수 없다)`() {
        // when
        val mineLaidSquares: Squares = twoByTwoSquares.mineLaid(1)

        val boundarySquare: Square = mineLaidSquares[0, 0]
        val mineSquare: Square = mineLaidSquares[1, 1]
        val emptySquare: Square = mineLaidSquares[1, 2]

        // then
        assertThat(boundarySquare.isMine()).isFalse()
        assertThat(mineSquare.isMine()).isTrue()
        assertThat(emptySquare.isMine()).isFalse()
    }

    @Test
    fun `지뢰 개수로 지점을 나타낼 수 있다`() {
        // given
        val spots = Squares.createFrom(Square(1, 1))

        // when
        val mineCountedSquares: Squares = spots.mineCounted { 3 }

        // then
        assertThat(spots[1, 1].currentState()).isEqualTo("0")
        assertThat(mineCountedSquares[1, 1].currentState()).isEqualTo("3")
    }

    @Test
    fun `지뢰 개수를 구할 수 있다`() {
        // given
        val mineLaidSquares: Squares = twoByTwoSquares.mineLaid(2)

        // when
        val mineCount: Int = mineLaidSquares.getMineCountOf(twoByTwoSquares[2, 2])

        // then
        assertThat(mineCount).isEqualTo(2)
    }
}
