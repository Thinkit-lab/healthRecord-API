# Hospital API Service

A comprehensive API service for managing medical records and staff profiles within a hospital setting. This API is designed for internal use by staff members and is intended for official use only.

## Features

- **Secure Staff Validation**: All requests require a staff member to pass their unique UUID as an authorization header for validation.

- **Staff Profile Management**: Add new staff members and update their profiles. A UUID is automatically generated for each staff member for subsequent API requests.

- **Patient Profile Management**: Access, download, and delete patient profiles, with a special focus on patients up to 2 years old. Validation of staff UUID is required for these operations.

- **Swagger Documentation**: Easy exploration of API endpoints with Swagger documentation.

## Endpoints

1. **Add Staff Member**
   - **Endpoint**: `/api/staff/create`
   - **Description**: Use this endpoint to add a new staff member to the service. A UUID is automatically generated for each staff member for subsequent API requests.
   - **Staff Profile**: ID, Name, UUID, Registration Date.

2. **Update Staff Profile**
   - **Endpoint**: `/api/staff/update`
   - **Description**: Update staff member profiles. Requires validation of the staff UUID before access.

3. **Fetch Patients Under 2 Years Old**
   - **Endpoint**: `/api/patient/fetch_patient_profile_withAgeUpto2Years`
   - **Description**: Fetch all patient profiles where age is up to 2 years old. Requires validation of the staff UUID before access.
   - **Patient Profile**: ID, Name, Age, Last Visit Date.

4. **Download Patient Profile as CSV**
   - **Endpoint**: `/api/patient/download_patient_profile/{patientId}`
   - **Description**: Download a specific patient's profile as a CSV file. Requires validation of the staff UUID before access.

5. **Delete Patient Profiles Within Date Range**
   - **Endpoint**: `/api/patient/delete_multiple_patient_profile`
   - **Description**: Delete multiple patient profiles within a specified date range. Requires validation of the staff UUID before access.

## Technologies

- **Spring Boot**: The core framework for building the API service.
- **Spring Data JPA**: For easy and efficient data access.
- **H2 Database**: A lightweight, in-memory database for development and testing.
- **Swagger**: Comprehensive API documentation for better usability.

## Getting Started

1. **Clone the Repository**:
   ```shell
   git clone https://github.com/Thinkit-lab/healthRecord-API.git
   
2. **Build and Run**:
   cd hospital-api-service
   mvn clean install
   mvn spring-boot:run

3. **Access the API Documentation**:
   Swagger UI: http://localhost:5000/swagger-ui.html

4. **Usage**
   - Create a staff profile to obtain a UUID for subsequent requests.
   - Use the UUID as an authorization header for validation.
   - Access various API endpoints to manage staff and patient profiles.
