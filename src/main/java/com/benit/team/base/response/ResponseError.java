package com.benit.team.base.response;

import lombok.Getter;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter

//Standard type of Error Response
public class ResponseError extends BaseResponse {
  private String message;
  private Map<String, String> errors;
}
