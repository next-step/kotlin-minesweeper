package minesweeper.domain

import minesweeper.domain.BoardBuilder.Companion.`⬜`
import minesweeper.domain.BoardBuilder.Companion.`💣`
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class OperationTest {
    @Test
    internal fun `열릴 위치를 입력받으면 셀이 열려있다`() {
        val board = board {
            row(`⬜`, `💣`, `⬜`)
        }.build()

        val operation = board.operation()
        operation.open(Position(1, 1))

        assertThat(operation.result()).isEqualTo(Operation.Result.SUCCESS)
        assertThat(board[0].open).isTrue()
    }

    @Test
    internal fun `열려 있는 곳을 다시 열면 이미 열려있음 응답을 받는다`() {
        val board = board {
            row(`⬜`, `💣`)
        }.build()

        val operation = board.operation()
        operation.open(Position(1, 1))
        operation.open(Position(1, 1))

        assertThat(operation.result()).isEqualTo(Operation.Result.OPENED)
    }

    @Test
    internal fun `지뢰를 열면 폭파됨 응답을 받는다`() {
        val board = board {
            row(`⬜`, `💣`)
        }.build()

        val operation = board.operation()
        operation.open(Position(2, 1))

        assertThat(operation.result()).isEqualTo(Operation.Result.EXPLOSION)
    }

    @Test
    internal fun `열었을 때 옆 셀이 비어있으면 같이 열린다`() {
        val board = board {
            row(`⬜`, `⬜`, `💣`)
        }.build()

        val operation = board.operation()
        operation.open(Position(1, 1))

        assertThat(board[0].open).isTrue()
        assertThat(board[1].open).isTrue()
    }

    @Test
    fun `열었을 때 옆 셀이 여러개 있으면 같이 열린다`() {
        val board = board {
            row(`⬜`, `⬜`, `⬜`, `💣`)
        }.build()

        val operation = board.operation()
        operation.open(Position(1, 1))

        assertThat(board)
            .filteredOnAssertions { assertThat(it.open).isTrue() }
            .hasSize(3)

        assertThat(board)
            .filteredOnAssertions { assertThat(it.open).isFalse() }
            .hasSize(1)
    }

    @Test
    internal fun `열었을 때 다른 줄도 닫혀있으면 열린다`() {
        val board = board {
            row(`⬜`, `⬜`, `⬜`, `⬜`, `💣`)
            row(`⬜`, `⬜`, `⬜`, `⬜`, `⬜`)
            row(`💣`, `⬜`, `⬜`, `⬜`, `⬜`)
        }.build()

        val operation = board.operation()
        operation.open(Position(1, 1))

        assertThat(board)
            .filteredOnAssertions { assertThat(it.open).isTrue() }
            .hasSize(13)
            .allSatisfy { assertThat(it.bomb).isFalse() }

        assertThat(board)
            .filteredOnAssertions { assertThat(it.open).isFalse() }
            .hasSize(2)
            .allSatisfy { assertThat(it.bomb).isTrue() }
    }

    @Test
    fun `지뢰카운트가 1 이상이면 더 열리지 않는다`() {
        val board = board {
            row(`⬜`, `⬜`, `⬜`)
            row(`⬜`, `⬜`, `💣`)
        }.build()

        val operation = board.operation()
        operation.open(Position(1, 1))

        assertThat(board[1].open).isTrue()
        assertThat(board[2].open).isFalse()
    }

    @Test
    fun `모두 열리면 종료응답을 받는다`() {
        val board = board {
            row(`⬜`, `💣`)
        }.build()
        val operation = board.operation()
        operation.open(Position(1, 1))

        assertThat(operation.result()).isEqualTo(Operation.Result.END)
    }

    @Test
    fun `영역을 벗어난곳을 열면 OUT_OF_MATRIX 응답을 받는다`() {
        val board = board {
            row(`⬜`, `💣`)
        }.build()

        val operation = board.operation()
        operation.open(Position(10, 10))

        assertThat(operation.result()).isEqualTo(Operation.Result.OUT_OF_MATRIX)
    }

    @Test
    internal fun `한방에 열 수 있다`() {
        val board = board {
            row(`⬜`, `💣`)
        }.build()

        board.allOpen()

        assertThat(board).allSatisfy {
            assertThat(it.open).isTrue()
        }
    }
}
