package ru.cbgr.autotest.enums;

/**
 * Основные URL для тестирования
 */
@SuppressWarnings("unused")
public enum URLS {
    TEST_STAND("localhost:4444", "TEST");

    private final String url;
    private final String standName;

    URLS(String url, String standName) {
        this.url = url;
        this.standName = standName;
    }

    /** @return получить название текущего стенда */
    public String getStandName() {
        return standName;
    }

    /** @return получить полный url текущего стенда */
    public String getWebUiUrl() {
        return "http://" + url + "/" ;
    }

    /** Выполняет выбор стенда сравнивая входящую строку.
     * Если нет совпадений, то запускается TEST_STAND по умолчанию
     * @param standName название стенда */
    public static String getStandUrlFromString(String standName) {
        for (var stand : values())
            if (stand.standName.equalsIgnoreCase(standName))
                return stand.getWebUiUrl();
        return TEST_STAND.getWebUiUrl();
    }
}