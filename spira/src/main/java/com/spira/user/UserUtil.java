package com.spira.user;

import com.constants.SpiraConstants;
import com.util.SpiraUtil;
import org.json.JSONObject;

public class UserUtil {

    public static User getCurrentUser() throws Exception {
        JSONObject jsonObject =
                SpiraUtil.getAPIRequestJSONObject(_getGetEndPoint());

        String userId = jsonObject.get("UserId").toString();

        User user = new User();

        user.setUserId(Integer.valueOf(userId));

        return user;
    }

    private static String _getGetEndPoint() {
        return SpiraConstants.BASE_ENDPOINT + "users";
    }
}
