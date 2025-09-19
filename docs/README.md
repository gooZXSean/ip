# Goober — User Guide

> A friendly, fast, keyboard-first task assistant with a clean JavaFX UI.  
> Keep your tasks in plain text, search them instantly, and stay in flow.

---

## ✅ Quick Start

1. **Requirements**
    - Java **17** or later installed (`java -version` to check)

2. **Run Goober**
    - **From JAR:**  
      `java -jar goober.jar`
    - **From source (IntelliJ/Gradle):** run the `Main` class

3. **Start typing commands** in the input box at the bottom, press **Enter** to submit.
    - **Shift+Enter** inserts a newline (won’t send).
    - Use **Clear** to clear the chat area.
    - Use the **Light/Dark** toggle to switch themes.
    - In our theme, the **background image** switches automatically:
        - Dark → *nebula* photo
        - Light → *grassy field*

> All tasks **auto-save** to disk when you add/update/delete them. No extra step needed.

---

## 🖥️ Interface at a Glance

- **Top Toolbar**
    - **Goober** — app title
    - **Light/Dark** toggle — switch themes
    - **Clear** — clears the conversation area (does not delete tasks)

- **Center Panel**
    - Scrollable history of your commands & Goober’s replies (chat bubbles)

- **Bottom Input Row**
    - **Command box** — type your commands
    - **Send** — click or press **Enter** to submit

---

## 💾 Where Your Data Lives

- Goober stores tasks in a simple text file so they persist between runs:
    - Default: `./data/goober.txt` (created automatically on first save)
- You can back up or sync this file like any other document.

---

## ✍️ Commands

Here’s the minimal set most users need. Every command returns a confirmation message.

| Action            | Command format                                                | Example                                                           |
|-------------------|---------------------------------------------------------------|-------------------------------------------------------------------|
| **Add ToDo**      | `todo DESCRIPTION`                                            | `todo read CS2103T user guide`                                    |
| **Add Deadline**  | `deadline DESCRIPTION /by YYYY-MM-DD HHMM`                    | `deadline submit iP /by 2025-09-19 2359`                          |
| **Add Event**     | `event DESCRIPTION /from YYYY-MM-DD HHMM /to YYYY-MM-DD HHMM` | `event project meeting /from 2025-09-24 1000 /to 2025-09-24 1200` |
| **List tasks**    | `list`                                                        | `list`                                                            |
| **Mark done**     | `mark INDEX`                                                  | `mark 2`                                                          |
| **Unmark**        | `unmark INDEX`                                                | `unmark 2`                                                        |
| **Delete**        | `delete INDEX`                                                | `delete 3`                                                        |
| **Find**          | `find KEYWORD`                                                | `find CS2103T`                                                    |
| **Set Priority**  | `priority INDEX /p LEVEL` *(LEVEL: HIGH, MEDIUM, LOW, NONE)*  | `priority 2 /p HIGH`                                              |
| **Help**          | `help`                                                        | `help`                                                            |
| **Exit**          | `bye`                                                         | `bye`                                                             |

> **INDEX** is the number shown by `list` (starting at 1).

### Notes on dates
- Use `YYYYY-MM-DD HHMM` for dates (e.g., `2025-09-21 1800`).
- `HHMM YYYYY-MM-DD` is also accepted.

---
