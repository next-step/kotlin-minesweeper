package domain

import fixture.row
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineBoardTest : FunSpec({
    test("지뢰찾기 보드를 만든다.") {
        val mineCoordinates: Set<Coordinate> = setOf(
            Coordinate(0, 0),
            Coordinate(1, 1),
            Coordinate(2, 2),
        )

        val mineBoard = MineBoard.create(
            height = 3,
            width = 3,
            mineCount = mineCoordinates.size,
            mineCoordinateGenerator = { _ -> mineCoordinates },
        )

        mineBoard.rows shouldBe listOf(
            row(Cell.MINE, Cell.CLOSED, Cell.CLOSED),
            row(Cell.CLOSED, Cell.MINE, Cell.CLOSED),
            row(Cell.CLOSED, Cell.CLOSED, Cell.MINE),
        )
    }

    test("지뢰찾기 보드를 만들 때, 전체 칸 수보다 지뢰 개수가 많으면 예외가 발생한다.") {
        val exception = shouldThrow<IllegalArgumentException> {
            MineBoard.create(
                height = 3,
                width = 3,
                mineCount = 10,
                mineCoordinateGenerator = RandomMineCoordinateGenerator(3, 3),
            )
        }

        exception.message shouldBe "보드 전체 칸 수(9)보다 지뢰가 많을 수 없습니다."
    }
})
