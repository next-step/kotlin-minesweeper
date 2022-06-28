package domain

import constants.ErrorMessages

/**
 * 게임에 대한 정보를 갖는 클래스.
 * Created by Jaesungchi on 2022.06.28..
 */
data class GameSettingInfo(val height: Int, val width: Int, val mineCount: Int) {
    init {
        require(height > 0) { ErrorMessages.IS_UNDER_ZERO }
        require(width > 0) { ErrorMessages.IS_UNDER_ZERO }
        require(mineCount >= 0) { ErrorMessages.IS_UNDER_ZERO }
    }
}
