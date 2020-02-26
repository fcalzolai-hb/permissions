package com.babylon.permissions.sysytem;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

@Component
@Profile("!test")
public class SystemNowProvider implements NowProvider {
  @Override
  public OffsetDateTime nowUtc() {
    return OffsetDateTime.now(ZoneOffset.UTC);
  }
}
