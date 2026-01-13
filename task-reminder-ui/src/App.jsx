import { useEffect, useState } from "react"
import { fetchTasks, exportCsv } from "./services/api"
import AddTask from "./components/AddTask"
import TaskList from "./components/TaskList"

function App() {
  const [tasks, setTasks] = useState([])

  function loadTasks() {
    fetchTasks().then(data => setTasks(data))
  }

  async function handleExport() {
    try {
      await exportCsv()
    } catch {
      alert("CSV export failed")
    }
  }

  useEffect(() => {
    loadTasks()
  }, [])

  return (
    <div className="app" style={{ padding: "20px" }}>
      <h1>Task Reminder App</h1>

      <button
        onClick={handleExport}
        style={{
          marginBottom: "20px",
          padding: "10px 16px",
          fontSize: "14px",
          cursor: "pointer"
        }}
      >
        Export CSV
      </button>

      <AddTask onTaskAdded={loadTasks} />
      <TaskList tasks={tasks} onTaskCompleted={loadTasks} />
    </div>
  )
}

export default App
