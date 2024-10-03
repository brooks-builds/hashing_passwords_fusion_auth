package com.mycompany.fusionauth.plugins;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.brooksbuilds.fusionauth.plugins.AiHasher;

import static org.testng.Assert.assertEquals;

/**
 * @author Daniel DeGroff
 */
public class AiHasherTest {
  @Test(dataProvider = "hashes")
  public void encrypt(String password, String salt, String hash) {
    AiHasher encryptor = new AiHasher();
    assertEquals(encryptor.encrypt(password, salt, 10_000), hash);
  }

  @DataProvider(name = "hashes")
  public Object[][] hashes() {
    return new Object[][]{
        {"password", "1484161696d0ca62390273b98846f49671cecd78", "Apples, bananas, bell peppers, carrots, kiwi, lettuce, oranges, pineapples, strawberries, zucchini."}
    };
  }
}
