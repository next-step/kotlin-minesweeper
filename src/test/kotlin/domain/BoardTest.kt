package domain

import domain.vo.Point
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

    "Board 의 범위를 벗어나는 cell 을 요청하면 에러가 발생한다" {
        val rows = listOf(
            createRow(createEmpty(1, 1), createMine(2, 1)),
            createRow(createMine(1, 2), createEmpty(2, 2)),
        )
        val board = Board.of(rows)

        val exception = shouldThrow<IllegalArgumentException> {
            board.getCell(createCoordinate(1, 3))
        }

        exception.message shouldBe "존재하지 않는 cell 입니다"
    }

    "open" - {
        "지뢰가 있는 cell 을 열면 패배한다" {
            val rows = listOf(
                createRow(createEmpty(1, 1), createMine(2, 1)),
                createRow(createMine(1, 2), createEmpty(2, 2)),
            )
            val board = Board.of(rows)

            val status = board.open(createCoordinate(1, 2))

            status shouldBe GameStatus.LOST
        }

        "지뢰가 없는 곳을 모두 열면 승리한다" {
            val rows = listOf(
                createRow(createEmpty(1, 1), createMine(2, 1)),
                createRow(createMine(1, 2), createEmpty(2, 2)),
            )
            val board = Board.of(rows)

            board.open(createCoordinate(1, 1))
            val status = board.open(createCoordinate(2, 2))

            status shouldBe GameStatus.WIN
        }

        "아직 열지않은 지뢰가 없는 곳이 남았다면 게임을 계속 진행한다" {
            val rows = listOf(
                createRow(createEmpty(1, 1), createMine(2, 1)),
                createRow(createMine(1, 2), createEmpty(2, 2)),
            )
            val board = Board.of(rows)

            val status = board.open(createCoordinate(1, 1))

            status shouldBe GameStatus.CONTINUE
        }

        "지뢰가 없는 인접한 곳을 모두 연다" {
            val rows = listOf(
                createRow(createEmpty(1, 1), createEmpty(2, 1), createEmpty(3, 1)),
                createRow(createEmpty(1, 2), createEmpty(2, 2), createMine(3, 2)),
                createRow(createEmpty(1, 3), createEmpty(2, 3), createEmpty(3, 3)),
                createRow(createMine(1, 4), createEmpty(2, 4), createEmpty(3, 4)),
            )
            val board = Board.of(rows)

            board.open(createCoordinate(1, 1))

            rows
                .flatMap { it.cells }
                .filterIsInstance<Empty>()
                .filter { it.opened }
                .map { "(${it.x}, ${it.y})" } shouldBe listOf(
                "(1, 1)",
                "(2, 1)",
                "(1, 2)",
                "(2, 2)",
                "(1, 3)",
                "(2, 3)",
            )
        }
    }
})

fun createCoordinate(x: Int, y: Int): Coordinate = Coordinate(Point(x), Point(y))
