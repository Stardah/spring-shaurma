package com.kpo.springshaurma.mapper;

import org.mapstruct.Mapper;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneId;

@Mapper(componentModel = "spring")
public interface MapperHelper {

  default OffsetDateTime localDateTime2OffsetDateTime(LocalDateTime localDateTime) {
    if (localDateTime == null) {
      return null;
    }

    return localDateTime.atOffset(ZoneId.systemDefault().getRules().getOffset(localDateTime));
  }

  default LocalDateTime offsetDateTime2LocalDateTime(OffsetDateTime offsetDateTime){
    if(offsetDateTime == null)
      return null;

    return offsetDateTime.toLocalDateTime();
  }
}
