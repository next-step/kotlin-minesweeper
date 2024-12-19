package minesweeper

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.startWith
import minesweeper.model.TestMinesStrategy

/**
 * @author 이상준
 */
class MinesWeeperServiceTest : StringSpec({
    "서비스를 통한 셀 생성 테스트" {
        val height = 2
        val width = 2
        val mineCount = 1
        val minesWeeperService = MinesWeeperService(height, width, mineCount, TestMinesStrategy())

        minesWeeperService.createCells().size() shouldBe 4
    }
    "서비스를 통한 셀 생성 예외 테스트1 " {
        val height = 2
        val width = 2
        val mineCount = 5

        val exception =
            shouldThrow<IllegalArgumentException> {
                MinesWeeperService(height, width, mineCount, TestMinesStrategy())
            }
        exception.message should startWith("지뢰 개수는 셀의 개수보다 작아야합니다.")
    }
    "서비스를 통한 셀 생성 예외 테스트 2" {
        val height = 0
        val width = 2
        val mineCount = 5

        val exception =
            shouldThrow<IllegalArgumentException> {
                MinesWeeperService(height, width, mineCount, TestMinesStrategy())
            }
        exception.message should startWith("높이, 너비, 지뢰 개수는 0보다 커야합니다.")
    }
    "서비스를 통한 셀 생성 예외 테스트 3" {
        val height = 2
        val width = 0
        val mineCount = 5

        val exception =
            shouldThrow<IllegalArgumentException> {
                MinesWeeperService(height, width, mineCount, TestMinesStrategy())
            }
        exception.message should startWith("높이, 너비, 지뢰 개수는 0보다 커야합니다.")
    }
    "서비스를 통한 셀 생성 예외 테스트 4" {
        val height = 2
        val width = 2
        val mineCount = 0

        val exception =
            shouldThrow<IllegalArgumentException> {
                MinesWeeperService(height, width, mineCount, TestMinesStrategy())
            }
        exception.message should startWith("높이, 너비, 지뢰 개수는 0보다 커야합니다.")
    }
})

