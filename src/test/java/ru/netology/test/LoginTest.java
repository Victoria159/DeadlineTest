package ru.netology.test;

import lombok.val;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataHelper;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;


import java.sql.SQLException;

import static com.codeborne.selenide.Selenide.open;
import static org.junit.jupiter.api.Assertions.*;

public class LoginTest {

    @AfterAll
    @DisplayName("Очистить базу данных после входа в систему")
    static void cleanBase() throws SQLException {
        val dashboardPage = new DashboardPage();
        dashboardPage.cleanDataBase();
    }

    @Test
    @DisplayName("Логин с валидными данными")
    void shouldLoginWithValidData() {
        open("http://localhost:9999");
        val loginPage = new LoginPage();
        val authInfo = DataHelper.getAuthInfo();
        val verificationCodePage = loginPage.validLogin(authInfo);
        DashboardPage dashboardPage = verificationCodePage.validVerify(DataHelper.getVerificationCode());
        assertEquals("Личный кабинет", dashboardPage.getHeading());
    }

}

