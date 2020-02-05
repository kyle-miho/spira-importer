package com.getter;

import com.constants.SpiraConstants;
import com.spira.project.ProjectUtil;
import com.spira.user.User;
import com.spira.user.UserUtil;

public class UserGetter {

    public static User getCurrentUser() throws Exception {
        if (_currentUser == null) {
            _currentUser = UserUtil.getCurrentUser();
        }

        return _currentUser;
    }

    private static User _currentUser;
}
