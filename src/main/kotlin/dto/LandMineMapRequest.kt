package dto

class LandMineMapRequest(val height: Int, val width: Int, val landMine: Int) {

    companion object {

        const val CANNOT_CONVERT_INT = "정수로 변환할 수 업습니다."
        const val ZERO_NEGATIVE_ERROR = "0 또는 음수는 입력할 수 없습니다."

        fun of(height: String, width: String, landMine: String): LandMineMapRequest {
            return LandMineMapRequest(
                convertInt(height),
                convertInt(width),
                convertInt(landMine)
            )
        }

        private fun convertInt(string: String): Int {
            val convertInt = string.toIntOrNull() ?: throw IllegalArgumentException(CANNOT_CONVERT_INT)
            checkLessZero(convertInt)
            return convertInt
        }

        private fun checkLessZero(number: Int) {
            if (number <= 0) throw IllegalArgumentException(ZERO_NEGATIVE_ERROR)
        }
    }
}
