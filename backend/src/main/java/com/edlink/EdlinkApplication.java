package com.edlink;

import java.security.SecureRandom;

import com.edlink.filters.JWTFeature;
import com.edlink.services.auth.LoginService;
import com.edlink.services.auth.RegisterService;
import com.edlink.services.feed.TestService;
import com.edlink.utils.AppConstants;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class EdlinkApplication extends Application<EdlinkConfiguration> {

  public static void main(final String[] args) throws Exception {
    new EdlinkApplication().run(args);
  }

  @Override
  public void run(final EdlinkConfiguration edlinkConfiguration,
      final Environment environment) throws Exception {

    new SecureRandom().nextBytes(AppConstants.sharedSecret);

    final RegisterService registerService = new RegisterService();
    final LoginService loginService = new LoginService();
    final TestService testService = new TestService();

    environment.jersey().register(registerService);
    environment.jersey().register(loginService);
    environment.jersey().register(testService);

    environment.jersey().register(JWTFeature.class);
  }
}
