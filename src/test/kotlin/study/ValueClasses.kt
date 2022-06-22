package study

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.assertAll

@JvmInline
value class ValueInt(private val value: Int)

@JvmInline
value class ValueString(private val value: String)

class ValueClassesTest {

    @Test
    fun `ValueInt class 비교 테스트`() {

        val expectedNot = 1
        val actual = ValueInt(expectedNot)

        assertAll(
            { assertThat(actual).isNotEqualTo(expectedNot) },
            { assertThat(actual).isNotSameAs(expectedNot) }
        )

        // syntax error
        // actual == 1, actual > 1, actual <1
        // actual + 1
    }

    @Test
    fun `ValueString class 비교 테스트`() {

        val expectedNot = "This is value string"
        val actual = ValueString(expectedNot)

        assertAll(
            { assertThat(actual).isNotEqualTo(expectedNot) },
            { assertThat(actual).isNotSameAs(expectedNot) }
        )

        // syntax error
        // actual.length
        // actual.subString(0,3)
    }
}
