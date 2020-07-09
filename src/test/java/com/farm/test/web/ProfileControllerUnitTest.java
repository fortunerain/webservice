package com.farm.test.web;

import com.farm.test.web.controller.ProfileController;
import org.junit.Test;
import org.springframework.mock.env.MockEnvironment;

import static org.assertj.core.api.Assertions.assertThat;

public class ProfileControllerUnitTest {

  @Test
  public void getRealProfile() {
    //given
    String expectedProfile = "real";
    MockEnvironment env = new MockEnvironment();
    env.addActiveProfile(expectedProfile);
    env.addActiveProfile("oauth");
    env.addActiveProfile("real-db");

    ProfileController profileController = new ProfileController(env);

    //when
    String profile = profileController.profile();

    //then
    assertThat(profile).isEqualTo(expectedProfile);

  }

  @Test
  public void getRealProfileFirst() {
    //given
    String expectedProfile = "oauth";
    MockEnvironment env = new MockEnvironment();
    env.addActiveProfile(expectedProfile);
    env.addActiveProfile("real-db");

    ProfileController profileController = new ProfileController(env);

    //when
    String profile = profileController.profile();

    //then
    assertThat(profile).isEqualTo(expectedProfile);

  }

  @Test
  public void getDefaultProfile() {
    //given
    String expectedProfile = "default";
    MockEnvironment env = new MockEnvironment();

    ProfileController profileController = new ProfileController(env);

    //when
    String profile = profileController.profile();

    //then
    assertThat(profile).isEqualTo(expectedProfile);

  }
}