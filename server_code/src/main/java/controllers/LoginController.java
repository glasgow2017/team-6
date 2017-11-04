/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package controllers;

import com.google.inject.Inject;
import ninja.Result;
import ninja.Results;

import controllers.forms.LoginForm;

import com.google.inject.Singleton;
import ninja.session.FlashScope;
import ninja.session.Session;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import services.LoginService;

@Singleton
public class LoginController {

    @Inject
    LoginService loginService;

    public Result index() {
        return Results.html();
    }

    public Result VerifyLogin(LoginForm form, FlashScope flashScope, Session session) throws Exception
    {
        int loginId = loginService.VerifyLogin(form.email, form.password);
        if (loginId > 0)
        {
            session.put("username", form.email);
            session.put("id", new Integer(loginId).toString());
            if (form.remember != null)
            {
                // Set the expiry time 30 days (in milliseconds) in the future
                session.setExpiryTime(30 * 24 * 60 * 60 * 1000L);
            }
            return Results.redirect("/home");
        }
        else
        {
            flashScope.error("Email or password are invalid");
            return Results.html().template("/views/LoginController/index.ftl.html");
        }
    }

    public Result Logout(FlashScope flash, Session session)
    {
        session.clear();
        flash.success("Logged out");
        return Results.redirect("/login");
    }
}
