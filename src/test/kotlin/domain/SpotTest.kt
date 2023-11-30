package domain

import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.booleans.shouldBeTrue
import io.kotest.matchers.shouldBe

class SpotTest : StringSpec({
    "Spot은 초기에 닫힌 상태이다" {
        val spot = Spot(false)
        spot.isOpen().shouldBeFalse()
    }

    "Spot을 열면 open 상태가 된다" {
        val spot = Spot(false)
        spot.open()
        spot.isOpen().shouldBeTrue()
    }

    "지뢰가 있는 Spot을 생성하면 지뢰가 있다" {
        val spot = Spot(true)
        spot.hasMine.shouldBeTrue()
    }

    "지뢰가 없는 Spot을 생성하면 지뢰가 없다" {
        val spot = Spot(false)
        spot.hasMine.shouldBeFalse()
    }

    "지뢰가 없는 Spot이 열리면 주위 지뢰 수가 표시된다" {
        Spot.VALIDATE_NEAR_MINE_COUNT.forEach { mineCount ->
            val spot = Spot(false)
            spot.setNearMineCount(mineCount)
            spot.open().symbol shouldBe mineCount.toString()
        }
    }

    "주위 지뢰 수 0 ~ 8 이외의 숫자를 설정하면 예외가 발생한다" {
        listOf(-1, 9).forEach { mineCount ->
            val spot = Spot(false)
            shouldThrow<IllegalStateException> {
                spot.setNearMineCount(mineCount)
            }
        }
    }

    "지뢰가 있는 Spot이 열리면 지뢰가 표시된다" {
        Spot.VALIDATE_NEAR_MINE_COUNT.forEach { mineCount ->
            val spot = Spot(true)
            spot.setNearMineCount(mineCount)
            spot.open() shouldBe OpenStatus.MINE
        }
    }
})
