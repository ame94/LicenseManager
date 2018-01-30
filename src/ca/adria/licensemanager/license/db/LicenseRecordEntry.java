package ca.adria.licensemanager.license.db;

/**
 * This object contains all relevant data
 */
public class LicenseRecordEntry {

    /** LicenseNumber : String -- unique code for each license */
    private String LicenseNumber;

   /** CreatedBy : String -- minecraft in-game player name */
    private String CreatedBy;

    /** CreationDate : int -- unix timestamp of when book was signed */
    private int CreationDate;

    /** ExpirationDate : int -- unix timestamp of when license will expire. 0 if never/ */
    private int ExpirationDate;

    /** Revoked : boolean -- was the license invalidated by someone? */
    private boolean Revoked;


    public LicenseRecordEntry(String licenseNumber, String createdBy, int creationDate, int expirationDate) {
        LicenseNumber = licenseNumber;
        CreatedBy = createdBy;
        CreationDate = creationDate;
        ExpirationDate = expirationDate;
        Revoked = false;
    }

    public String getLicenseNumber() {
        return LicenseNumber;
    }

    public String getCreatedBy() {
        return CreatedBy;
    }

    public int getCreationDate() {
        return CreationDate;
    }

    public int getExpirationDate() {
        return ExpirationDate;
    }

    public boolean isRevoked() {
        return Revoked;
    }
}
