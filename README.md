# Project Setup and Usage Documentation

## Prerequisites
- Java 17
- Docker
- Docker Compose
- Gradle

## Project Setup

1. **Clone the repository**: Use the command `git clone https://github.com/DanilaZavjalovs/SupportService.git` to clone the repository to your local machine.

2. **Start the PostgreSQL database**: The project uses a PostgreSQL database running in a Docker container. The Docker Compose file for starting the database is located in the `docker` directory. Navigate to the `docker` directory and run the following command to start the database:

```bash
docker-compose up -d
```

3. **Build the project**: Navigate back to the project directory and run the command `./gradlew build` to build the project.

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

# User Registration

To register a new user, you send a POST request to an endpoint such as `/api/register`. The request body would contain the user's information, such as their username and password. Here is an example of a valid JSON body for user registration:

```json
{
    "username": "newuser",
    "password": "newpassword"
}
```

# Running Tests

To run tests in project, you can use the Gradle `test` task. Here's how you can do it:

1. Open a terminal in your project directory.

2. Run the following command:

```bash
./gradlew test
```

This command will compile your code, run your tests, and output the results to the console.

If you want to see more detailed output, you can use the `--info` or `--debug` flags:

```bash
./gradlew test --info
```

or

```bash
./gradlew test --debug
```

These commands will provide more detailed output about the test execution, including which tests were run and how long they took.
