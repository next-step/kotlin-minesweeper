package minesweeper.util

object NearbyIndicesCalculator {

    fun getNearbyIndices(index: Int, width: Int, height: Int): List<Int> {
        val nearbyIndices = mutableListOf<Int>()
        repeat(3) {
            val startIndex = it * width + index - width - 1
            val targetLine = if (startIndex + 1 >= 0) (startIndex + 1) / width else -1
            nearbyIndices += getNearbyIndicesLine(startIndex, targetLine, height, width, index)
        }
        return nearbyIndices
    }

    private fun getNearbyIndicesLine(
        startIndex: Int,
        targetLine: Int,
        height: Int,
        width: Int,
        index: Int
    ) = (startIndex..startIndex + 2).asSequence()
        .filter { targetLine < height }
        .filterNot { targetIndex -> targetIndex < 0 }
        .filterNot { targetIndex -> targetIndex < targetLine * width }
        .filterNot { targetIndex -> targetIndex > (targetLine + 1) * width - 1 }
        .filterNot { targetIndex -> targetIndex == index }
}
