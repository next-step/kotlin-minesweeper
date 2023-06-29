package tdd.minesweeper.application

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FreeSpec
import io.kotest.matchers.shouldBe
import tdd.minesweeper.domain.Point
import tdd.minesweeper.domain.type.GameProgressStatus
import tdd.minesweeper.fixture.RowsFixtureProvider
import tdd.minesweeper.fixture.create3x3Rows
import tdd.minesweeper.repository.inmemory.MineBoardInMemoryRepository
import tdd.minesweeper.ui.request.MineBoardCreateRequest
import java.lang.IllegalArgumentException

class MineSweeperServiceTest : FreeSpec({
    val service = MineSweeperService(
        mineBoardRepository = MineBoardInMemoryRepository,
        rowsProvider = RowsFixtureProvider
    )

    "지뢰판 생성을 할 수 있다." {
        val givenRows = create3x3Rows()
        RowsFixtureProvider.updateFixtureRows(rows = givenRows)

        val savedId =
            service.createMineBoard(request = MineBoardCreateRequest(width = 3, height = 3, mineCapacity = 2))

        val actual = service.findMineBoard(savedId)

        actual.rows shouldBe givenRows
    }

    "지뢰판 생성 이후 " - {
        RowsFixtureProvider.updateFixtureRows(rows = create3x3Rows())
        val savedId =
            service.createMineBoard(request = MineBoardCreateRequest(width = 3, height = 3, mineCapacity = 2))

        "유효한 좌표로 마킹하면 진행할 수 있다는 상태와 지뢰판이 반환된다." {
            val (actualBoard, actualStatus) = service.markPoint(id = savedId, point = Point(0, 0))

            actualStatus shouldBe GameProgressStatus.CONTINUE
            actualBoard.getRemainCount() shouldBe 3
        }

        "유효하지 않은 좌표로 마킹하려하면 예외를 던진다." {
            shouldThrow<IllegalArgumentException> {
                service.markPoint(id = savedId, point = Point(4, 0))
            }
        }

        "지뢰가 있는 좌표로 마킹하려고하면 패배 상태를 반환한다." {
            val (actualBoard, actualStatus) = service.markPoint(id = savedId, point = Point(2, 0))

            actualStatus shouldBe GameProgressStatus.LOSE
        }

        "모든 유효 좌표를 마킹하면 승리 상태를 반환한다." {
            listOf(Point(0, 0), Point(0, 2), Point(2, 1)).forEach {
                service.markPoint(savedId, it)
            }

            val (actualBoard, actualStatus) = service.markPoint(savedId, Point(2, 2))

            actualStatus shouldBe GameProgressStatus.WIN
        }
    }
})
