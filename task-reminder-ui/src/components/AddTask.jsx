import { useState, useRef } from "react"
import { addTask } from "../services/api"

function AddTask({ onTaskAdded }) {
  const [title, setTitle] = useState("")
  const [description, setDescription] = useState("")
  const [dueDate, setDueDate] = useState("")
  const dateInputRef = useRef(null)

  async function handleSubmit(e) {
    e.preventDefault()
    if (!title || !dueDate) return

    await addTask({ title, description, dueDate })

    setTitle("")
    setDescription("")
    setDueDate("")
    onTaskAdded()
  }

  return (
    <div style={{ marginBottom: "30px" }}>
      <h2>Add Task</h2>

      <form onSubmit={handleSubmit}>
        <input
          type="text"
          placeholder="Title"
          value={title}
          onChange={e => setTitle(e.target.value)}
        />

        <input
          type="text"
          placeholder="Description"
          value={description}
          onChange={e => setDescription(e.target.value)}
        />

        <input
          type="datetime-local"
          ref={dateInputRef}
          value={dueDate}
          onChange={e => setDueDate(e.target.value)}
        />

        <div style={{ display: "flex", gap: "10px", marginTop: "10px" }}>
          <button
            type="button"
            className="secondary-btn"
            onClick={() => dateInputRef.current.blur()}
          >
            Done
          </button>

          <button type="submit">Add Task</button>
        </div>
      </form>
    </div>
  )
}

export default AddTask
