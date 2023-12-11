package mineswipper.domain.map

@JvmInline
value class Pedals(
    val value: List<Pedal>
) {
    fun get(index: Int): Pedal {
        return value[index]
    }
}