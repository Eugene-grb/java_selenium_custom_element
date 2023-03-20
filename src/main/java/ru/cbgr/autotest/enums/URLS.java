package ru.cbgr.autotest.enums;

/**
 * Основные URL для тестирования
 */
@SuppressWarnings("unused")
public enum URLS {
    // для запуска на удаленных средствах запуска
    GRID_URL("localhost:4444", "GRID"),
    SELENOID_URL("localhost:4444", "SELENOID"),

    // тестовые стенды
    TEST_STAND("tracking.cbgr.ru/consyst", "TEST");

    private final String url;
    private final String standName;

    URLS(String url, String standName) {
        this.url = url;
        this.standName = standName;
    }

    public String getStandName() {
        return standName;
    }

    public String getRemoteUrl() {
        return "http://" + url + "/wd/hub/";
    }

    public String getWebUiUrl() {
        return "http://" + url + "/webui/index.html#/" ;
    }

    public static String getStandUrlFromString(String standName) {
        for (var stand : values())
            if (stand.standName.equalsIgnoreCase(standName))
                return stand.getWebUiUrl();
        return TEST_STAND.getWebUiUrl();
    }
}