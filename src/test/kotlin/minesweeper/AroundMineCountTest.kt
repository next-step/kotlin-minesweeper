package minesweeper

import ErrorCode
import domain.AroundMineCount
import io.kotest.assertions.throwables.shouldThrowWithMessage
import io.kotest.core.spec.style.StringSpec
import io.kotest.inspectors.forAll
import io.kotest.matchers.types.shouldBeInstanceOf

class AroundMineCountTest : StringSpec({

    "주변 지뢰 개수는 0 을 포함하는 양의 정수를 가질 수 있습니다" {
        listOf(0, 10).forAll { mineCount ->
            val aroundMineCount = AroundMineCount(mineCount)

            aroundMineCount.shouldBeInstanceOf<AroundMineCount>()
        }
    }

    "주변 지뢰 개수가 음수를 가지면 에러를 반환합니다" {
        val negativeNumber = -1

        shouldThrowWithMessage<IllegalArgumentException>(ErrorCode.INVALID_AROUND_MINE_COUNT_ERROR.msg) {
            AroundMineCount(negativeNumber)
        }
    }

    "주변 지뢰 개수가 clean(0) 한지 확인할 수 있다" {
        val aroundMineCount = AroundMineCount(0)

        aroundMineCount.isClean().shouldBeInstanceOf<Boolean>()
    }
})
