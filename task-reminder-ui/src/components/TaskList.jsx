import { markTaskCompleted, scheduleReminder } from "../services/api"

function TaskList({ tasks, onTaskCompleted }) {
  return (
    <div>
      <h2>Tasks</h2>

      {tasks.length === 0 && <p>No tasks found</p>}

      {tasks.map(task => (
        <div
          key={task.id}
          className={`task-card ${task.completed ? "completed" : ""}`}
        >
          <p><strong>{task.title}</strong></p>
          <p>{task.description}</p>
          <p>Due: {task.dueDate}</p>
          <p>Status: {task.completed ? "Completed" : "Pending"}</p>

          {!task.completed && (
            <>
              <button
                className="secondary-btn"
                onClick={async () => {
                  await markTaskCompleted(task.id)
                  onTaskCompleted()
                }}
              >
                Mark Completed
              </button>

              <div style={{ marginTop: "10px" }}>
                <input
                  type="datetime-local"
                  onChange={e => {
                    task._reminderTime = e.target.value
                  }}
                />

                <button
                  className="secondary-btn"
                  style={{ marginLeft: "8px" }}
                  onClick={async () => {
                    if (!task._reminderTime) return
                    await scheduleReminder(task.id, task._reminderTime)
                    alert("Reminder scheduled")
                  }}
                >
                  Schedule Reminder
                </button>
              </div>
            </>
          )}
        </div>
      ))}
    </div>
  )
}

export default TaskList
