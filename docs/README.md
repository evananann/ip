# Dog User Guide
Dog is a GUI-based task manager that tracks todos, deadlines and events with tagging for you to categorise
tasks easily.

## The Features and Commands

- **Viewing all tasks: `list`**
  - **Description:** Shows all tasks in your list with their status and any tag.
  - **Format:** `list`
  - **Example:**

    list

  - **Expected output:**

    ```
    Here are the tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: Dec 1 2024)
    3. [E][ ] project meeting (from: Mon 2pm to: 4pm)
    ```

- **Searching tasks: `find`**
  - **Description:** Searches for tasks whose string representation contains the given keyword.
  - **Format:** `find <keyword>`
  - **Example:**

    find book

  - **Expected output:**

    ```
    Here are the matching tasks in your list:
    1. [T][ ] read book
    2. [D][ ] return book (by: Dec 1 2024)
    ```

- **Adding a todo: `todo`**
  - **Description:** Adds a basic task with no time specification.
  - **Format:** `todo <description>`
  - **Example:**

    todo borrow book

  - **Expected output:**

    ```
    Got it. I've added this task:
      [T][ ] borrow book
    Now you have 4 tasks in the list.
    ```

- **Adding a deadline: `deadline`**
  - **Description:** Adds a task that must be completed by a specified date.
  - **Format:** `deadline <description> /by <yyyy-mm-dd>` (date must be ISO format)
  - **Example:**

    deadline return book /by 2024-12-01

  - **Expected output:**

    ```
    Got it. I've added this task:
      [D][ ] return book (by: Dec 1 2024)
    Now you have 4 tasks in the list.
    ```

- **Adding an event: `event`**
  - **Description:** Adds a task that occurs between two times or dates.
  - **Format:** `event <description> /from <start> /to <end>`
  - **Example:**

    event project meeting /from Mon 2pm /to 4pm

  - **Expected output:**

    ```
    Got it. I've added this task:
      [E][ ] project meeting (from: Mon 2pm to: 4pm)
    Now you have 4 tasks in the list.
    ```

- **Marking a task done: `mark`**
  - **Description:** Marks a task in the list as completed.
  - **Format:** `mark <task-number>`
  - **Example:**

    mark 1

  - **Expected output:**

    ```
    Alright! I have marked this task as done:
      [T][X] read book
    ```

- **Unmarking a task: `unmark`**
  - **Description:** Marks an already completed task as not done.
  - **Format:** `unmark <task-number>`
  - **Example:**

    unmark 1

  - **Expected output:**

    ```
    Okay! I have unmarked this task:
      [T][ ] read book
    ```

- **Deleting a task: `delete`**
  - **Description:** Removes a task in the list with its task number.
  - **Format:** `delete <task-number>`
  - **Example:**

    delete 2

  - **Expected output:**

    ```
    Okay. I have removed this task:
      [D][ ] return book (by: Dec 1 2024)
    There are now 3 tasks in the list.
    ```

- **Tagging a task: `tag`**
  - **Description:** Adds a tag label to a task. The label can be specified with or without a leading `#`.
  - **Format:** `tag <task-number> <label>` or `tag <task-number> #<label>`
  - **Example:**

    tag 3 fun

  - **Expected output:**

    ```
    Okay! I tagged the task:
      [E][ ] project meeting #fun (from: Mon 2pm to: 4pm)
    ```

- **Exiting the app: `bye`**
  - **Description:** Saves and exits the application.
  - **Format:** `bye`
  - **Example:**

    bye

  - **Expected output:**

    ```
    Bye! Catch you later!
    ```

## Things to Note

- Dates for `deadline` must be provided in `yyyy-mm-dd` format; they are displayed as `MMM d yyyy` (e.g., `Dec 1 2024`).
- Task numbers used with `mark`, `unmark`, `delete`, and `tag` are 1-based.
- Search performed by `find` matches the task's full string representation.

