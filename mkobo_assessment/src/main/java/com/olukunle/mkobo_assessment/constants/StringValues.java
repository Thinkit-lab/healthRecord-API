package com.olukunle.mkobo_assessment.constants;

public interface StringValues {
    String AUTHORIZATION_KEY = "Authorization";
    String LOGGER_STRING_GET = "Request url -> {} response -> {}";
    String LOGGER_STRING_POST = "Request url -> {} payload -> {} response -> {}";
    String STAFF_RECORD_SAVED = "Staff record saved successfully";
    String STAFF_RECORD_UPDATED = "Staff record updated successfully";
    String NO_PATIENT_RECORD_FOUND = "No patient record found with the query parameter";
    String NO_PATIENT_RECORD_FOUND_WITH_ID = "No patient record found with the provided id: ";
    String FAILED_WRITING_PROFILE_AS_CSV = "Writing profile as csv failed";
    String SUCCESS_WRITING_PROFILE_AS_CSV = "Successfully written patient profile as csv; file downloaded";
    String RECORD_FOUND = "Record found";
    String SUCCESSFUL_RECORD_DELETE = "Patient record deleted successfully";
}
