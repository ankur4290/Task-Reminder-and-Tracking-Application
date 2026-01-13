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


