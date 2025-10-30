# CLI Task Tracker
This is a simple **Command Line Interface (CLI)**  application 
that helps you manage your tasks. You can add, update, delete
and list tasks as well as filter tasks based on their status.
Tasks are store in a JSON file (`tasks.json`), which will be 
automatically created in the project directory when the 
application is first run.

## Features

- **Add**: Creates a new task with a name.
- **Update**: Mark a task as `in_progress` or `done`.
- **Delete**: Remove a task from the list.
- **List**: View all task or filter by status `todo`, 
`in_progress` and `done`.

## Getting Started

### Prerequisites

- Java JDK 17 or higher installed in your machine.

### Installation
1. Download the jar file in the directory 
`out/artifacts/task-cli_jar/task-cli.jar`

### Usage

- **Add a task**: Once the task is created, it will have
the status `todo`
```shell
java -jar task-cli.jar add <task-name>
```
- **Update a Task's status**
```shell
java -jar task-cli.jar update <task-id> <task-name>
```
- Delete a Task
```shell
java -jar task-cli.jar delete <task-id>
```
- List
  - List All Tasks
  ```shell
  java -jar task-cli.jar list
  ```
  - List Tasks by Status
  ```shell
  # To list all tasks that are not done
  java -jar task-cli.jar list todo
  
  # To list all tasks that are done
  java -jar task-cli.jar list done
  
  # To list all tasks that are in progress
  java -jar task-cli.jar list in-progress
  ```
## Example of Usage
```shell
# Add tasks
java -jar task-cli.jar add "Touch grass"
java -jar task-cli.jar add "Go to the gym"

# List tasks
java -jar task-cli.jar list

# Update task status
java -jar task-cli.jar mark-in-progress 1

# List tasks in progress
java -jar task-cli.jar list in-progress

#Delete a task
java -jar task-cli.jar delete 1
```
## Notes

- All tasks are stored in a task.json