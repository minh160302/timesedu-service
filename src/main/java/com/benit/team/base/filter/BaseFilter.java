package com.benit.team.base.filter;

import lombok.Getter;

@Getter
public class BaseFilter {
  protected int limit;
  protected int page;

  public void setLimit(int limit) {
    if (limit > 250)
      limit = 250;

    this.limit = limit;
  }

  public void setPage(int page) {
    if (page < 0)
      page = 0;

    this.page = page;
  }
}
