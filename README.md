# 📌 Task Management System  
-  **Mini project for my Fundamental Programming Techniques assignment of my second year university course**

> **Task Management System** is a Java FX desktop application designed to organize employees and their assigned tasks efficiently.  
> It supports creating simple and complex tasks, assigning them, updating progress, and generating useful workload statistics.  
> All data is persisted through serialization, making it easy to save and restore work.  

---

## ✨ Features  

- 👥 **Employee Management** – Add and view employees with their assigned tasks  
- 📝 **Task Management**  
  - Create **Simple Tasks** (duration based on start and end hours)  
  - Create **Complex Tasks** (composed of multiple simple or complex tasks using the **Composite Pattern**)  
- 📌 **Task Assignment** – Assign tasks to employees with real-time workload calculation  
- ✅ **Task Status Updates** – Mark tasks as **Completed** or **Uncompleted**  
- ⏳ **Work Duration Calculation** – Automatically estimate each employee’s workload based on completed tasks  
- 📊 **Statistics Dashboard**  
  - Employees with workload > 40 hours (sorted ascending)  
  - Completed vs Uncompleted tasks per employee  
- 💾 **Data Persistence** – Save and load all data using **serialization**  

---

## 🛠 Tech Stack  

| Technology | Purpose |
|------------|---------|
| **Java 17+** | Core programming language |
| **Swing (Java UI)** | Graphical user interface |
| **Serialization** | Data persistence |
| **OOP Principles** | Modular and maintainable design |
| **Composite Design Pattern** | Task hierarchy (simple & complex tasks) |
| **Sealed Classes** | Structured and restricted inheritance |

---

## 🚀 How It Works  

1. Add employees and define tasks (simple or complex).  
2. Assign tasks to employees and track their progress.  
3. Modify task statuses as work progresses.  
4. Analyze workload and task completion through built-in statistics.  
5. Save all progress and restore it later with serialization.  

---

## 📂 Project Structure  

- **Model Layer** → Employee, Task (sealed), SimpleTask, ComplexTask  
- **Service Layer** → Task assignment, workload calculation, status management  
- **Utility Layer** → Statistics and data analysis  
- **UI Layer** → Swing-based interface for interaction  

---
