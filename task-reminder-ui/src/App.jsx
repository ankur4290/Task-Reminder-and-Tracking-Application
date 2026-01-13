import { useEffect, useState } from "react"
import { fetchTasks } from "./services/api"
import AddTask from "./components/AddTask"
import TaskList from "./components/TaskList"

function App() {
  const [tasks, setTasks] = useState([])

  function loadTasks() {
    fetchTasks().then(data => {
      setTasks(data)
    })
  }

  useEffect(() => {
    loadTasks()
  }, [])

  return (
    <div className="app">
      <h1>Task Reminder App</h1>
      <AddTask onTaskAdded={loadTasks} />
      <TaskList tasks={tasks} onTaskCompleted={loadTasks} />
    </div>
  )
}

export default App
