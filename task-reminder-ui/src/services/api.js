const BASE_URL = "http://localhost:8081"

export async function fetchTasks() {
  const response = await fetch(`${BASE_URL}/tasks`)
  return response.json()
}

export async function addTask(task) {
  const response = await fetch(`${BASE_URL}/tasks`, {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify(task)
  })

  return response.json()
}

export async function markTaskCompleted(taskId) {
  await fetch(`${BASE_URL}/completion/mark/${taskId}`, {
    method: "PUT"
  })
}

export async function scheduleReminder(taskId, reminderTime) {
  await fetch("http://localhost:8081/schedule/set", {
    method: "POST",
    headers: {
      "Content-Type": "application/json"
    },
    body: JSON.stringify({
      taskId,
      reminderTime
    })
  })
}
export async function exportCsv() {

  const response = await fetch("http://localhost:8081/reports/export", {
    method: "POST"
  })


  if (!response.ok) {
    throw new Error("CSV export failed")
  }

  const text = await response.text()

  const blob = new Blob([text], { type: "text/csv;charset=utf-8;" })
  const url = window.URL.createObjectURL(blob)

  const a = document.createElement("a")
  a.href = url
  a.download = "tasks.csv"
  document.body.appendChild(a)
  a.click()

  document.body.removeChild(a)
  window.URL.revokeObjectURL(url)
}




