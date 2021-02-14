package com.benit.team.base.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

// Standard type of Data Response
public class ResponseData<T> extends BaseResponse {
  private Meta meta;
  private T data;

  @Data
  public static class Meta {
    private String message;
    private Integer page;
    private Integer pageSize;
    private Long total;
    private Boolean success;
  }
}
