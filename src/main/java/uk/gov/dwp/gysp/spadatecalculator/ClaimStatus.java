package uk.gov.dwp.gysp.spadatecalculator;

public enum ClaimStatus {
    MATURE_CLAIM("Mature claim"),
    PRE_MATURE_CLAIM("Pre-Mature claim"),
    NON_STATE_PENSION_CUSTOMER("Non state pension customer");

    private final String description;

    ClaimStatus(final String description) {
        this.description = description;
    }

    public String getDescription() {
        return this.description;
    }
}
