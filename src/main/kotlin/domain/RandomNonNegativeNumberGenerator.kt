package domain

class RandomNonNegativeNumberGenerator(
    private val to: Int,
    private val count: Int,
) : NumbersGenerator {
    override fun generate(): List<Int> {
        return (0 until to).shuffled()
            .take(count)
    }
}
