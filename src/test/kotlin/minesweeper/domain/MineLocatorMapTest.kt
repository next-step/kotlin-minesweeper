package minesweeper.domain

import io.kotest.assertions.throwables.shouldNotThrowAny
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class MineLocatorMapTest : FunSpec({
    context("객체 생성") {
        test("높이, 너비, 지뢰 개수를 입력받아 객체를 생성한다.") {
            shouldNotThrowAny {
                MineLocatorMap(1, 1, 1)
            }
        }
        test("지뢰 개수가 1 미만 또는 (높이 * 너비)를 초과할 경우 예외가 발생한다.") {
            shouldThrow<IllegalArgumentException> {
                MineLocatorMap(10, 10, 0)
                MineLocatorMap(10, 10, 10 * 10 + 1)
            }
        }
    }
    context("sites()") {
        test("지도 정보를 반환한다.") {
            (1..10000).forEach { countOfMine ->
                val mineLocatorMap = MineLocatorMap(100, 100, countOfMine)
                mineLocatorMap.sites.count { it.hasMine } shouldBe countOfMine
            }
        }
    }
})
