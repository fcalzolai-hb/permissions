package com.babylon.permissions.sysytem;

import java.time.OffsetDateTime;

public interface NowProvider {
  public OffsetDateTime nowUtc();
}
