# Project Setup and Usage Documentation


## Prerequisites
- Java 17
- Docker
- Gradle


## Project Setup

1. **Clone the repository**: Use the command `git clone <repository-url>` to clone the repository to your local machine.

2. **Build the project**: Navigate to the project directory and run the command `./gradlew build` to build the project.

3. **Start the PostgreSQL database**: The project uses a PostgreSQL database running in a Docker container. You can start the database using the following command:

```bash
docker run --name integration-tests-db -e POSTGRES_USER=username -e POSTGRES_PASSWORD=password -p 5432:5432 -d postgres
```


## Running the Application

1. **Start the application**: You can start the application by running the command `./gradlew bootRun` in the project directory.

## Using the Application

The application provides a REST API with endpoints for managing support agents. The API is documented using Swagger, which provides an interactive UI for exploring the API. You can access the Swagger UI at `http://localhost:8080/swagger-ui.html`.

Here is an example of how to use the `SupportAgentController`:

1. **Create a new support agent**: Send a POST request to `/api/supportAgent` with a JSON body representing the support agent. Here is an example of a valid JSON body:

```json
{
    "id": "3fa85f64-5717-4562-b3fc-2c963f66afa6",
    "firstname": "Test",
    "lastname": "Agent",
    "email": "test.agent@example.com",
    "phone": "1234567890",
    "statistics": {
        "ticketsResolved": 120,
        "averageResolutionTime": "3 hours",
        "customerSatisfactionScore": 4.8
    }
}
```

2. **Get a support agent by ID**: Send a GET request to `/api/supportAgent/{id}`, replacing `{id}` with the ID of the support agent you want to retrieve.

Please note that some endpoints require authentication. You can authenticate by providing a valid username(test) and password(testtest) in the `Authorization` header of your requests.
