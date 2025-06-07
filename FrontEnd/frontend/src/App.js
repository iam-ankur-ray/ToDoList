import { useState, useEffect } from "react";
import axios from "axios";

function App() {
  const [todos, setTodos] = useState([]);
  const [title, setTitle] = useState("");

  // Fetch all todos on load
  useEffect(() => {
    axios.get("http://localhost:8080/api/todos")
      .then(res => setTodos(res.data))
      .catch(err => console.error("Error fetching todos", err));
  }, []);

  // Create a new todo
  const addTodo = () => {
    if (!title.trim()) return;

    axios.post("http://localhost:8080/api/todos", {
      title: title,
      completed: false
    }).then(res => {
      setTodos([...todos, res.data]);
      setTitle(""); // clear input after add
    }).catch(err => console.error("Error adding todo", err));
  };

  return (
    <div style={{ padding: 20 }}>
      <h2>To-Do List</h2>

      <input
        value={title}
        onChange={e => setTitle(e.target.value)}
        placeholder="Enter task..."
      />
      <button onClick={addTodo}>Add</button>

      <ul>
        {todos.map(todo => (
          <li key={todo.id}>
            {todo.title} {todo.completed ? "✅" : "❌"}
          </li>
        ))}
      </ul>
    </div>
  );
}

export default App;
