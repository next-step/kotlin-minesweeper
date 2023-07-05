package minesweeper.domain

import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

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
})
