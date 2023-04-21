package com.kpo.springshaurma.api.models;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Page {

  private Integer number;

  private Integer size;

  private Integer totalPages;

  private Long totalElements;
}

