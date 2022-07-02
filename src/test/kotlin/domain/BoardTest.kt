package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe

internal class BoardTest : FreeSpec({

    "빈 리스트로는 인스턴스 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            Board.of(listOf())
        }
    }

    "주어진 각 row 크기가 동일하지 않으면 인스턴스 생성할 수 없다" {
        shouldThrow<IllegalArgumentException> {
            val rows = listOf(
                createRow(3, 1),
                createRow(3, 2),
                createRow(2, 3),
            )
            Board.of(rows)
        }
    }

    "Board 인스턴스를 생성할 수 있다" {
        val rows = listOf(
            createRow(3, 1),
            createRow(3, 2),
            createRow(3, 3),
        )
        val board = Board.of(rows)

        board.cellCount shouldBe 9
    }

    "주어진 cell 주변의 지뢰개수를 반환한다" {
        val rows = listOf(
            createRow(createEmpty(1, 1), createMine(2, 1)),
            createRow(createMine(1, 2), createEmpty(2, 2)),
        )
        val board = Board.of(rows)

        board.rows.flatMap { it.cells }.map { board.mineCount(it) } shouldBe listOf(2, 1, 1, 2)
    }

    "open" - {
        "지뢰가 있는 cell 을 열면 패배한다" {
            val rows = listOf(
                createRow(createEmpty(1, 1), createMine(2, 1)),
                createRow(createMine(1, 2), createEmpty(2, 2)),
            )
            val board = Board.of(rows)

            val status = board.open(board.rows[0].cells[1])

            status shouldBe GameStatus.LOST
        }

        "지뢰가 없는 곳을 모두 열면 승리한다" {
            val rows = listOf(
                createRow(createEmpty(1, 1), createMine(2, 1)),
                createRow(createMine(1, 2), createEmpty(2, 2)),
            )
            val board = Board.of(rows)

            board.open(board.rows[0].cells[0])
            val status = board.open(board.rows[1].cells[1])

            status shouldBe GameStatus.WIN
        }

        "아직 열지않은 지뢰가 없는 곳이 남았다면 게임을 계속 진행한다" {
            val rows = listOf(
                createRow(createEmpty(1, 1), createMine(2, 1)),
                createRow(createMine(1, 2), createEmpty(2, 2)),
            )
            val board = Board.of(rows)

            val status = board.open(board.rows[0].cells[0])

            status shouldBe GameStatus.CONTINUE
        }
    }
})
