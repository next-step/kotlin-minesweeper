package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineMapFactoryTest {
    private val strategyPickOrderly = PickStrategy { target, count ->
        target.locations
            .take(count)
            .forEach { it.pick() }
    }
    private val mineMapFactory = MineMapFactory(strategyPickOrderly)

    @Test
    fun `기본지뢰매니저를 사용해 지뢰맵을 만드는 결과 확인해보기`() {
        val meta = MineMapMeta(3, 5)

        val mineCount = 10

        val mineMap = mineMapFactory.create(meta, mineCount)

        assertThat(mineMap.rows).isEqualTo(
            listOf(
                Row(listOf(true, true, true, true, false)),
                Row(listOf(true, true, true, false, false)),
                Row(listOf(true, true, true, false, false)),
            )
        )
    }
}
