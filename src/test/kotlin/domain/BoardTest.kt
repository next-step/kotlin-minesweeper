package domain

import fixture.board
import fixture.cell
import fixture.mine
import fixture.row
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.datatest.withData
import io.kotest.matchers.shouldBe

class BoardTest : FunSpec({
    test("지뢰찾기 보드를 만든다.") {
        val mineCoordinates: Set<Coordinate> = setOf(
            Coordinate(0, 0),
            Coordinate(1, 1),
            Coordinate(2, 2),
        )

        val board = Board.create(
            height = 3,
            width = 3,
            mineCount = mineCoordinates.size,
            mineCoordinateGenerator = { _ -> mineCoordinates },
        )

        board shouldBe board(
            row(mine(), cell(), cell()),
            row(cell(), mine(), cell()),
            row(cell(), cell(), mine()),
        )
    }

    test("지뢰찾기 보드를 만들 때, 전체 칸 수보다 지뢰 개수가 많으면 예외가 발생한다.") {
        val exception = shouldThrow<IllegalArgumentException> {
            Board.create(
                height = 3,
                width = 3,
                mineCount = 10,
                mineCoordinateGenerator = RandomMineCoordinateGenerator(3, 3),
            )
        }

        exception.message shouldBe "보드 전체 칸 수(9)보다 지뢰가 많을 수 없습니다."
    }

    context("지뢰찾기 보드 특정 좌표에 마인이 있는지 여부를 반환한다") {
        val board = board(
            row(mine(), cell()),
            row(cell(), mine()),
        )

        data class CoordinateIsMine(val coordinate: Coordinate, val expected: Boolean)
        withData(
            CoordinateIsMine(Coordinate(0, 0), true),
            CoordinateIsMine(Coordinate(0, 1), false),
            CoordinateIsMine(Coordinate(1, 0), false),
            CoordinateIsMine(Coordinate(1, 1), true),
        ) { (coordinate, expected) ->
            board.hasMine(coordinate) shouldBe expected
        }
    }

    context("지뢰찾기 보드 특정 좌표가 닫혀있는지 여부를 반환한다") {
        val board = board(
            row(mine(), cell(Opened(AroundMineCount.TWO))),
            row(cell(), mine()),
        )

        data class CoordinateIsMine(val coordinate: Coordinate, val expected: Boolean)
        withData(
            CoordinateIsMine(Coordinate(0, 0), true),
            CoordinateIsMine(Coordinate(0, 1), false),
            CoordinateIsMine(Coordinate(1, 0), true),
            CoordinateIsMine(Coordinate(1, 1), true),
        ) { (coordinate, expected) ->
            board.isClosed(coordinate) shouldBe expected
        }
    }

    context("지뢰찾기 보드 셀을 열면 주변 지뢰 개수로 변경한다") {
        val board = board(
            row(mine(), mine(), cell()),
            row(cell(), cell(), cell()),
            row(mine(), cell(), cell()),
        )

        data class CoordinateIsMine(val coordinate: Coordinate, val expectedCell: Cell)
        withData(
            nameFn = { "When ${it.coordinate} opens, cell should be ${it.expectedCell}" },
            CoordinateIsMine(Coordinate(0, 0), mine()),
            CoordinateIsMine(Coordinate(0, 1), mine()),
            CoordinateIsMine(Coordinate(0, 2), cell(Opened(AroundMineCount.ONE))),
            CoordinateIsMine(Coordinate(1, 0), cell(Opened(AroundMineCount.THREE))),
            CoordinateIsMine(Coordinate(1, 1), cell(Opened(AroundMineCount.THREE))),
            CoordinateIsMine(Coordinate(1, 2), cell(Opened(AroundMineCount.ONE))),
            CoordinateIsMine(Coordinate(2, 0), mine()),
            CoordinateIsMine(Coordinate(2, 1), cell(Opened(AroundMineCount.ONE))),
            CoordinateIsMine(Coordinate(2, 2), cell(Opened(AroundMineCount.ZERO))),
        ) { (coordinate, expectedCell) ->
            board.open(coordinate)
            board[coordinate.row][coordinate.col] shouldBe expectedCell
        }
    }

    test("지뢰찾기 보드 셀을 열어 0이면, 지뢰가 아닌 주변 셀을 계속해서 연다") {
        val board = board(
            row(mine(), mine(), cell(), cell()),
            row(cell(), cell(), cell(), cell()),
            row(mine(), cell(), cell(), cell()),
            row(mine(), cell(), cell(), cell()),
        )
        
        board.open(Coordinate(2, 2))
        board shouldBe board(
            row(mine(), mine(), cell(1), cell(0)),
            row(cell(), cell(3), cell(1), cell(0)),
            row(mine(), cell(2), cell(0), cell(0)),
            row(mine(), cell(2), cell(0), cell(0)),
        )
    }

    test("지뢰찾기 보드에서 닫힌 셀 개수가 지뢰 개수보다 많으면 게임은 진행중이다") {
        val board = board(
            row(mine(), mine(), cell(Opened(AroundMineCount.ONE))),
            row(cell(Opened(AroundMineCount.THREE)), cell(Opened(AroundMineCount.THREE)), cell(Opened(AroundMineCount.ONE))),
            row(mine(), cell(), cell(Opened(AroundMineCount.ZERO))),
        )

        board.isRunning() shouldBe true
    }

    test("지뢰찾기 보드에서 닫힌 셀 개수가 지뢰 개수보다 적거나 같으면 게임은 진행중이 아니다") {
        val board = board(
            row(mine(), mine(), cell(Opened(AroundMineCount.ONE))),
            row(cell(Opened(AroundMineCount.THREE)), cell(Opened(AroundMineCount.THREE)), cell(Opened(AroundMineCount.ONE))),
            row(mine(), cell(Opened(AroundMineCount.ONE)), cell(Opened(AroundMineCount.ZERO))),
        )

        board.isRunning() shouldBe false
    }
})
