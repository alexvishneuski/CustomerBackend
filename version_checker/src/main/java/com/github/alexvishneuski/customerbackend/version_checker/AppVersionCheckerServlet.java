/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Servlet Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloWorld
*/

package com.github.alexvishneuski.customerbackend.version_checker;

import com.example.AppVersion;
import com.google.gson.Gson;

import java.io.IOException;

import javax.servlet.http.*;

public class AppVersionCheckerServlet extends HttpServlet {

    //TODO make separate json doc with versionNumber, access throuth interface
    private Integer appVersionId = 1;

    @Override
    public void doGet(final HttpServletRequest pRequest, final HttpServletResponse pResponse)
            throws IOException {
        pResponse.setContentType("application/json");

        final AppVersion version = new AppVersion();

            version.setId(appVersionId);

        //TODO what is faster?

        //1
        new Gson().toJson(version, pResponse.getWriter());

        //2
//        final String versionAsString = new Gson().toJson(version);
//        pResponse.getWriter().print(versionAsString);
    }



    @Override
    public void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws IOException {
        String name = req.getParameter("name");
        resp.setContentType("text/plain");
        if (name == null) {
            resp.getWriter().println("Please enter a name");
        }
        resp.getWriter().println("Hello " + name);
    }
}
