import { useEffect, useState } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../App.css"; // ✅ make sure this is imported

function Dashboard() {
  const [tasks, setTasks] = useState([]);
  const [statusFilter, setStatusFilter] = useState("");
  const navigate = useNavigate();

  // ✅ FETCH TASKS (with backend filter)
  useEffect(() => {
    const token = localStorage.getItem("token");

    let url = "http://localhost:8080/api/tasks";

    if (statusFilter) {
      url += `?status=${statusFilter}`;
    }

    axios
      .get(url, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => setTasks(res.data))
      .catch((err) => console.log(err));
  }, [statusFilter]);

  // ✅ DELETE
  const handleDelete = async (id) => {
    const token = localStorage.getItem("token");

    try {
      await axios.delete(`http://localhost:8080/api/tasks/${id}`, {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      });

      setTasks(tasks.filter((task) => task.id !== id));
    } catch (err) {
      console.log(err);
    }
  };

  // ✅ MARK DONE (NO RELOAD)
  const markDone = async (id) => {
    const token = localStorage.getItem("token");

    try {
      await axios.put(
        `http://localhost:8080/api/tasks/${id}`,
        { status: "DONE" },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      // ✅ update UI instantly
      setTasks(
        tasks.map((task) =>
          task.id === id ? { ...task, status: "DONE" } : task
        )
      );

    } catch (err) {
      console.log(err.response);
    }
  };

  return (
    <div className="container">
      <h2>Dashboard</h2>

      <button
        className="create-btn"
        onClick={() => navigate("/create")}
      >
        Create Task
      </button>

      <br /><br />

      {/* ✅ FILTER */}
      <select onChange={(e) => setStatusFilter(e.target.value)}>
        <option value="">All</option>
        <option value="TODO">TODO</option>
        <option value="IN_PROGRESS">IN PROGRESS</option>
        <option value="DONE">DONE</option>
      </select>

      <br /><br />

      {tasks.length === 0 ? (
        <p>No tasks</p>
      ) : (
        tasks.map((task) => (
          <div className="card" key={task.id}>
            <h3>{task.title}</h3>
            <p>{task.description}</p>

            {/* ✅ STATUS WITH COLOR */}
            <p
              style={{
                color:
                  task.status === "DONE"
                    ? "green"
                    : task.status === "IN_PROGRESS"
                    ? "orange"
                    : "gray",
              }}
            >
              {task.status}
            </p>

            {/* ✅ ASSIGNED USER */}
            <p>
              Assigned To: {task.assignedTo?.name || "Not Assigned"}
            </p>

            <button
              className="delete-btn"
              onClick={() => handleDelete(task.id)}
            >
              Delete
            </button>

            <button
              className="done-btn"
              onClick={() => markDone(task.id)}
            >
              Mark Done
            </button>
          </div>
        ))
      )}
    </div>
  );
}

export default Dashboard;