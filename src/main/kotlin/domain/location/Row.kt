package domain.location

import domain.setting.Height
import kotlin.random.Random

@JvmInline
value class Row(val value: Int) {
    companion object {
        fun makeAtRandom(until: Height) =
            Random.nextInt(until.value)
                .let(::Row)
    }
}
