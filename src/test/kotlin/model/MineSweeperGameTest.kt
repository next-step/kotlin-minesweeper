package model

import io.kotest.core.spec.style.StringSpec
import io.kotest.matchers.shouldBe

class MineSweeperGameTest : StringSpec({

    "높이 너비 지뢰수를 받아 지뢰찾기 게임 객체를 생성한다" {
        // when
        val mineSweeperGame = MineSweeperGame(10, 10, 10)

        // then
        mineSweeperGame.getCells().size shouldBe 10
    }
})
