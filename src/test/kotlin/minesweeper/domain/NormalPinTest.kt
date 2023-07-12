package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe
import minesweeper.domain.pin.MinePin
import minesweeper.domain.pin.NormalPin

class NormalPinTest : FunSpec({
    test("pin 주변에 지뢰의 개수를 대표하는 프로퍼티를 증가시킬 수 있다") {
        val pin = NormalPin()

        pin.addSurroundMineNumber()
        pin.addSurroundMineNumber()

        pin.surroundMineNumber shouldBe 2
    }

    test("비교대상인 핀이 지뢰인 경우, 지뢰 숫자 프로퍼티가 증가한다") {
        val pin = NormalPin()
        val mine = MinePin()

        pin.comparePinType(mine)

        pin.surroundMineNumber shouldBe 1
    }

    test("초기 정상 핀의 상태는 CLOSE 상태이다") {
        val pin = NormalPin()

        pin.isOpenable() shouldBe true
    }

    test("정상 핀은 지뢰 핀으로 상태가 변경될 수 있다") {
        val normalPin = NormalPin()

        val minePin = normalPin.changeToMine()

        minePin.isMinePin() shouldBe true
    }
})
