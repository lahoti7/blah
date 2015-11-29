package com.edlink;

import com.edlink.services.auth.LoginService;
import com.edlink.services.auth.RegisterService;
import io.dropwizard.Application;
import io.dropwizard.setup.Environment;

public class EdlinkApplication extends Application<EdlinkConfiguration> {

  public static void main(final String[] args) throws Exception {
    new EdlinkApplication().run(args);
  }

  @Override
  public void run(final EdlinkConfiguration edlinkConfiguration,
      final Environment environment) throws Exception {
    final RegisterService registerService = new RegisterService();
    final LoginService loginService = new LoginService();

    environment.jersey().register(registerService);
    environment.jersey().register(loginService);
  }
}
