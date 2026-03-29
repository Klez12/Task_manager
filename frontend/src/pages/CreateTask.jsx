import { useState, useEffect } from "react";
import axios from "axios";
import { useNavigate } from "react-router-dom";
import "../App.css"; // ✅ IMPORTANT

function CreateTask() {
  const [title, setTitle] = useState("");
  const [description, setDescription] = useState("");
  const [users, setUsers] = useState([]);
  const [assignedUser, setAssignedUser] = useState("");

  const navigate = useNavigate();

  // ✅ FETCH USERS
  useEffect(() => {
    const token = localStorage.getItem("token");

    axios
      .get("http://localhost:8080/api/users", {
        headers: {
          Authorization: `Bearer ${token}`,
        },
      })
      .then((res) => setUsers(res.data))
      .catch((err) => console.log(err));
  }, []);

  // ✅ CREATE TASK
  const handleSubmit = async () => {
    const token = localStorage.getItem("token");

    if (!title || !description) {
      alert("Please fill all fields");
      return;
    }

    try {
      await axios.post(
        "http://localhost:8080/api/tasks",
        {
          title,
          description,
          status: "TODO",
          assignedTo: assignedUser
            ? { id: assignedUser }
            : null, // ✅ handle empty case
        },
        {
          headers: {
            Authorization: `Bearer ${token}`,
          },
        }
      );

      alert("Task Created");
      navigate("/dashboard");

    } catch (err) {
      console.error(err);
      alert("Error creating task");
    }
  };

  return (
    <div className="container">
      <div className="card">
        <h2>Create Task</h2>

        <input
          placeholder="Title"
          onChange={(e) => setTitle(e.target.value)}
        />

        <br /><br />

        <input
          placeholder="Description"
          onChange={(e) => setDescription(e.target.value)}
        />

        <br /><br />

        {/* ✅ USER DROPDOWN */}
        <select onChange={(e) => setAssignedUser(e.target.value)}>
          <option value="">Select User</option>
          {users.map((user) => (
            <option key={user.id} value={user.id}>
              {user.name}
            </option>
          ))}
        </select>

        <br /><br />

        <button className="create-btn" onClick={handleSubmit}>
          Create Task
        </button>
      </div>
    </div>
  );
}

export default CreateTask;