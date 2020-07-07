package com.farm.test.web.dto;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class SelectOption {
  private final String key;
  private final String name;
  private String selected;

  public void setSelected(String selected) {
    this.selected = selected;
  }
}
