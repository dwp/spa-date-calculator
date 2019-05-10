package uk.gov.dwp.gysp.spadatecalculator;

public enum Gender {
    FEMALE("Female"), MALE("Male");

    private final String genderType;

    private Gender(final String genderType) {
        this.genderType = genderType;
    }

    public static Gender genderOf(final String gender) {
        for (Gender value : Gender.values()) {
            if (value.getGender().equals(gender)) {
                return value;
            }
        }
        return null;
    }

    public String getGender() {
        return this.genderType;
    }
}
