package com.optimagrowth.organization.utils;

import org.springframework.stereotype.Component;

/**
 * Хранит значения из HTTP заголовков из запроса, отправленного клиентом
 */
@Component
public class UserContext {
    public static final String CORRELATION_ID = "tmx-correlation-id";
    public static final String AUTH_TOKEN = "tmx-auth-token";
    public static final String USER_ID = "tmx-user-id";
    public static final String ORG_ID = "tmx-org-id";

    private static String correlationId = new String();
    private static String authToken = new String();
    private static String userId = new String();
    private static String orgId = new String();

    public static String getCorrelationId() {
        return correlationId;
    }

    public static void setCorrelationId(String cid) {
        correlationId = cid;
    }

    public static String getAuthToken() {
        return authToken;
    }

    public static void setAuthToken(String aToken) {
        authToken = aToken;
    }

    public static String getUserId() {
        return userId;
    }

    public static void setUserId(String aUser) {
        userId = aUser;
    }

    public static String getOrgId() {
        return orgId;
    }

    public static void setOrgId(String aOrg) {
        orgId = aOrg;
    }

}
