package tdd.minesweeper.domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.type.GameProgressStatus
import tdd.minesweeper.fixture.create3x3Rows

class MineBoardTest : FunSpec({

    test("유효한 좌표이고 마킹 가능할 경우 마킹하며, 마킹 가능 횟수를 차감하고 진행 상황을 반환한다.") {
        val board = MineBoard(area = Area(3, 3), rows = create3x3Rows())

        val actual: GameProgressStatus = board.marking(Point(0, 0))

        actual shouldBe GameProgressStatus.CONTINUE
        board.getRemainCount() shouldBe 3
    }

    test("유효한 좌표이고 마킹 불가능할 경우, 변경없이 CONTINUE 상태를 반환한다.") {
        val board = MineBoard(area = Area(3, 3), rows = create3x3Rows())

        board.marking(Point(0, 0))
        val actual: GameProgressStatus = board.marking(Point(0, 0))

        actual shouldBe GameProgressStatus.CONTINUE
        board.getRemainCount() shouldBe 3
    }

    test("유효하지 않은 좌표일 경우 예외를 던진다.") {
        val board = MineBoard(area = Area(3, 3), rows = create3x3Rows())

        shouldThrow<IllegalArgumentException> {
            board.marking(Point(4, 4))
        }
    }

    test("지뢰가 있는 위치를 마킹하려하면 LOSE 상태를 반환한다.") {
        val board = MineBoard(area = Area(3, 3), rows = create3x3Rows())

        val actual = board.marking(Point(2, 0))

        actual shouldBe GameProgressStatus.LOSE
    }

    test("지뢰가 아닌 위치를 모두 마킹하면 WIN을 반환한다.") {
        val board = MineBoard(area = Area(3, 3), rows = create3x3Rows())

        listOf(Point(0, 0), Point(0, 2), Point(2, 1)).forEach(board::marking)

        val actual = board.marking(Point(2, 2))
        actual shouldBe GameProgressStatus.WIN
    }

    test("마킹한 좌표가 1이상인 경우 인접 좌표를 밝히지 않는다.") {
        val board = MineBoard(area = Area(3, 3), rows = create3x3Rows())

        board.marking(Point(2, 2))

        board.getRemainCount() shouldBe 6
    }
})
