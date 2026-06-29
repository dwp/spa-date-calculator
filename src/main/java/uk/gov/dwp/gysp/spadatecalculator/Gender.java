package uk.gov.dwp.gysp.spadatecalculator;

import org.apache.commons.lang3.StringUtils;

public enum Gender {
  FEMALE("Female"),
  MALE("Male");

  private final String genderType;

  Gender(final String genderType) {
    this.genderType = genderType;
  }

  public static Gender genderOf(final String genderParameter) {
    for (final Gender gender : Gender.values()) {
      if (StringUtils.equals(gender.getGender(), genderParameter)) {
        return gender;
      }
    }
    
    return null;
  }

  public String getGender() {
    return this.genderType;
  }
}
