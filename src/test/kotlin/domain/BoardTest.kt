package domain

import fixture.mineBoard
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

        board shouldBe mineBoard(
            row(Cell.MINE, Cell.CLOSED, Cell.CLOSED),
            row(Cell.CLOSED, Cell.MINE, Cell.CLOSED),
            row(Cell.CLOSED, Cell.CLOSED, Cell.MINE),
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
        val mineBoard = mineBoard(
            row(Cell.MINE, Cell.CLOSED),
            row(Cell.CLOSED, Cell.MINE),
        )

        data class CoordinateIsMine(val coordinate: Coordinate, val expected: Boolean)
        withData(
            CoordinateIsMine(Coordinate(0, 0), true),
            CoordinateIsMine(Coordinate(0, 1), false),
            CoordinateIsMine(Coordinate(1, 0), false),
            CoordinateIsMine(Coordinate(1, 1), true),
        ) { (coordinate, expected) ->
            mineBoard.hasMine(coordinate) shouldBe expected
        }
    }

    context("지뢰찾기 보드 특정 좌표가 닫혀있는지 여부를 반환한다") {
        val mineBoard = mineBoard(
            row(Cell.MINE, Cell.CLOSED),
            row(Cell.CLOSED, Cell.MINE),
        )

        data class CoordinateIsMine(val coordinate: Coordinate, val expected: Boolean)
        withData(
            CoordinateIsMine(Coordinate(0, 0), false),
            CoordinateIsMine(Coordinate(0, 1), true),
            CoordinateIsMine(Coordinate(1, 0), true),
            CoordinateIsMine(Coordinate(1, 1), false),
        ) { (coordinate, expected) ->
            mineBoard.isClosed(coordinate) shouldBe expected
        }
    }

    context("지뢰찾기 보드 셀을 주변 지뢰 개수로 변경한다(open)") {
        val mineBoard = mineBoard(
            row(Cell.MINE, Cell.MINE, Cell.CLOSED),
            row(Cell.CLOSED, Cell.CLOSED, Cell.CLOSED),
            row(Cell.MINE, Cell.CLOSED, Cell.CLOSED),
        )

        data class CoordinateIsMine(val coordinate: Coordinate, val expectedCell: Cell)
        withData(
            nameFn = { "When ${it.coordinate} opens, cell should be ${it.expectedCell}" },
            CoordinateIsMine(Coordinate(0, 0), Cell.MINE),
            CoordinateIsMine(Coordinate(0, 1), Cell.MINE),
            CoordinateIsMine(Coordinate(0, 2), Cell.ONE),
            CoordinateIsMine(Coordinate(1, 0), Cell.THREE),
            CoordinateIsMine(Coordinate(1, 1), Cell.THREE),
            CoordinateIsMine(Coordinate(1, 2), Cell.ONE),
            CoordinateIsMine(Coordinate(2, 0), Cell.MINE),
            CoordinateIsMine(Coordinate(2, 1), Cell.ONE),
            CoordinateIsMine(Coordinate(2, 2), Cell.ZERO),
        ) { (coordinate, expectedCell) ->
            mineBoard.open(coordinate)
            mineBoard[coordinate.row][coordinate.col] shouldBe expectedCell
        }
    }

    test("지뢰찾기 보드에서 닫힌 셀이 하나라도 있으면 게임은 진행중이다") {
        val mineBoard = mineBoard(
            row(Cell.MINE, Cell.MINE, Cell.ONE),
            row(Cell.THREE, Cell.THREE, Cell.ONE),
            row(Cell.MINE, Cell.CLOSED, Cell.ZERO),
        )

        mineBoard.isRunning() shouldBe true
    }

    test("지뢰찾기 보드에서 닫힌 셀이 없으면 게임은 진행중이 아니다") {
        val mineBoard = mineBoard(
            row(Cell.MINE, Cell.MINE, Cell.ONE),
            row(Cell.THREE, Cell.THREE, Cell.ONE),
            row(Cell.MINE, Cell.ONE, Cell.ZERO),
        )

        mineBoard.isRunning() shouldBe false
    }
})
