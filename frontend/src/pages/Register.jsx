import { useState } from "react";
import { registerUser } from "../api/authApi";

function Register({ onNavigateToLogin }) {
  const [form, setForm] = useState({ username: "", password: "", confirmPassword: "" });
  const [error, setError] = useState("");
  const [success, setSuccess] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setError("");
    setSuccess("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (form.password !== form.confirmPassword) {
      setError("Passwords do not match");
      return;
    }

    try {
      setLoading(true);
      await registerUser({ username: form.username, password: form.password });
      setSuccess("Account created successfully! Redirecting to login...");
      setForm({ username: "", password: "", confirmPassword: "" });
      setTimeout(() => {
        if (onNavigateToLogin) {
          onNavigateToLogin();
        }
      }, 1500);
    } catch (err) {
      setError(err.response?.data || "Registration failed. Try a different username.");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-card-container">
      <div className="login-card">
        <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "15px" }}>
          <h3>Join Sweet Gallery</h3>
          <p className="login-subtitle">Create an account to order fresh sweets</p>
          
          {error && <p style={{ color: "var(--color-danger)", textAlign: "center", fontWeight: "600", fontSize: "0.9rem" }}>{error}</p>}
          {success && <p style={{ color: "var(--color-success)", textAlign: "center", fontWeight: "600", fontSize: "0.9rem" }}>{success}</p>}
          
          <input
            type="text"
            name="username"
            placeholder="Choose Username"
            value={form.username}
            onChange={handleChange}
            required
            minLength={3}
          />
          
          <input
            type="password"
            name="password"
            placeholder="Password"
            value={form.password}
            onChange={handleChange}
            required
            minLength={4}
          />

          <input
            type="password"
            name="confirmPassword"
            placeholder="Confirm Password"
            value={form.confirmPassword}
            onChange={handleChange}
            required
          />
          
          <button type="submit" disabled={loading}>
            {loading ? "Registering..." : "Sign Up"}
          </button>

          <div style={{ textAlign: "center", marginTop: "10px" }}>
            <p style={{ fontSize: "0.9rem", color: "#888" }}>
              Already have an account?{" "}
              <button 
                type="button" 
                onClick={onNavigateToLogin} 
                style={{ 
                  background: "none", 
                  border: "none", 
                  color: "var(--color-primary)", 
                  cursor: "pointer", 
                  fontWeight: "600",
                  textDecoration: "underline",
                  padding: 0
                }}
              >
                Login
              </button>
            </p>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Register;
