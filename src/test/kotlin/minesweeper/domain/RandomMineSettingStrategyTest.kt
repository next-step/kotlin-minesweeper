package minesweeper.domain

import io.kotest.core.spec.style.ExpectSpec
import io.kotest.matchers.shouldBe

/**
 * @see RandomMineSettingStrategy
 */
class RandomMineSettingStrategyTest : ExpectSpec({

    expect("전체 블록 개수 x와 지뢰 개수 y를 입력하면 x이하의 중복없는 랜덤 숫자 y개를 돌려준다") {
        val randomStrategy = RandomMineSettingStrategy()
        val locations = randomStrategy.getLocations(9, 2)

        locations.toSet().size shouldBe 2
        locations.forEach { it in 1 until 9 }
    }
})
