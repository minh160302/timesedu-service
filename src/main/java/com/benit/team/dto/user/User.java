package com.benit.team.dto.user;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {
  @JsonProperty("_id")
  private String id;

  @JsonProperty("full_name")
  private String fullName;

  @JsonProperty("activation_key")
  private String activationKey;

  private String avatar;
  private String email;
  private String password;
  private String address;
  private Boolean activated;

  @JsonProperty("last_modified_date")
  private String lastModifiedDate;

  @JsonProperty("created_date")
  private String createdDate;
}
