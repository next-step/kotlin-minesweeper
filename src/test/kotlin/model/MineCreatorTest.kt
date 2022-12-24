package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineCreatorTest : StringSpec({

    "지뢰 생성 메소드를 실행하면, 지뢰 갯수 만큼의 set 가 반환된다" {
        // when
        val createMine = MineCreator.createMine(10, 10, 11)

        // then
        createMine.size shouldBe 11
    }
})
