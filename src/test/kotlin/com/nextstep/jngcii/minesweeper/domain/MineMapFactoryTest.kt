package com.nextstep.jngcii.minesweeper.domain

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test

class MineMapFactoryTest {
    private val manager = MineManager(
        booleanStrategy = { true },
        pickStrategy = { target, count -> target.take(count) }
    )
    private val mineMapFactory = MineMapFactory(manager)

    @Test
    fun `기본지뢰매니저를 사용해 지뢰맵을 만드는 결과 확인해보기`() {
        val rowCount = 3
        val columnCount = 5

        val mineCount = 10

        val actual = mineMapFactory.create(rowCount, columnCount, mineCount)

        val expected = MineMap(
            listOf(
                Row(listOf(true, true, true, true, false)),
                Row(listOf(true, true, true, false, false)),
                Row(listOf(true, true, true, false, false)),
            )
        )

        assertThat(actual).isEqualTo(expected)
    }
}
