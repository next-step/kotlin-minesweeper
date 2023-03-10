package domains

enum class OpenResult(val message: String) {
    WIN("게임을 이겼습니다"),
    LOSE("지뢰를 밟아서 게임을 패배했습니다"),
    CONTINUE("계속"),
    ;

    companion object {
        fun fromMineBlockCheck(isMineOpen: Boolean): OpenResult {
            if (isMineOpen) return LOSE
            return CONTINUE
        }

        fun fromNormalBlockCheck(isAllOpenNormalBlock: Boolean): OpenResult {
            if (isAllOpenNormalBlock) return WIN
            return CONTINUE
        }
    }
}
