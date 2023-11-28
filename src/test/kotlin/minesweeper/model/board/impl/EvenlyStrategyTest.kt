package minesweeper.model.board.impl

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe
import minesweeper.model.board.toBoardLimit

class EvenlyStrategyTest : StringSpec({

    "지뢰를 생성하지 않는 전략도 가능해야한다" {
        val strategy = EvenlyStrategy(0)
        val deployPoints = strategy.deployPoints((10 to 10).toBoardLimit())
        deployPoints.countOfMine() shouldBe 0
    }

    "전체 중 약 10% 정도가 지뢰인 경우, 요청한 수 만큼 지뢰가 잘 생성 되어야 한다" {
        val strategy = EvenlyStrategy(10)
        val deployPoints = strategy.deployPoints((10 to 10).toBoardLimit())
        deployPoints.countOfMine() shouldBe 10
    }

    "전체 중 약 77% 정도가 지뢰인 경우, 요청한 수 만큼 지뢰가 잘 생성 되어야 한다" {
        val strategy = EvenlyStrategy(77)
        val deployPoints = strategy.deployPoints((10 to 10).toBoardLimit())
        deployPoints.countOfMine() shouldBe 77
    }

    "전체가 지뢰로 가득 찬 경우, 요청한 수 만큼 지뢰가 잘 생성 되어야 한다" {
        val strategy = EvenlyStrategy(25)
        val deployPoints = strategy.deployPoints((5 to 5).toBoardLimit())
        deployPoints.countOfMine() shouldBe 25
    }

    "지뢰의 최대 생성 가능 수 보다 많은 지뢰 생성 요청시 throw IllegalArgumentException" {
        val strategy = EvenlyStrategy(26)
        shouldThrow<IllegalArgumentException> {
            strategy.deployPoints((5 to 5).toBoardLimit())
        }
    }
})
