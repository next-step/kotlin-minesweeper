package domain.location

import domain.setting.Width
import kotlin.random.Random

@JvmInline
value class Column(val value: Int) {
    companion object {
        fun makeAtRandom(until: Width) =
            Random.nextInt(until.value)
                .let(::Column)
    }
}
