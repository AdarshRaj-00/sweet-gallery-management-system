import { useState } from "react";
import { login } from "../api/authApi";

function Login({ onLogin, onNavigateToRegister, onContinueAsGuest }) {
  const [form, setForm] = useState({ username: "", password: "" });
  const [error, setError] = useState("");
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setError("");
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      const res = await login(form);
      localStorage.setItem("token", res.data.token);
      setForm({ username: "", password: "" });
      if (onLogin) {
        onLogin();
      }
    } catch (err) {
      setError("Invalid username or password");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="login-card-container">
      <div className="login-card">
        <form onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "15px" }}>
          <h3>Sweet Gallery</h3>
          <p className="login-subtitle">Fresh & Authentic Indian Sweets</p>
          
          {error && <p style={{ color: "var(--color-danger)", textAlign: "center", fontWeight: "600", fontSize: "0.9rem" }}>{error}</p>}
          
          <input
            type="text"
            name="username"
            placeholder="Username"
            value={form.username}
            onChange={handleChange}
            required
          />
          
          <input
            type="password"
            name="password"
            placeholder="Password"
            value={form.password}
            onChange={handleChange}
            required
          />
          
          <button type="submit" disabled={loading}>
            {loading ? "Logging in..." : "Login to Shop"}
          </button>

          <div style={{ textAlign: "center", marginTop: "10px", display: "flex", flexDirection: "column", gap: "10px" }}>
            <p style={{ fontSize: "0.9rem", color: "#888", margin: 0 }}>
              Don't have an account?{" "}
              <button 
                type="button" 
                onClick={onNavigateToRegister} 
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
                Register
              </button>
            </p>
            <button 
              type="button"
              onClick={onContinueAsGuest}
              className="guest-btn"
              style={{
                background: "transparent",
                border: "1px solid var(--color-border)",
                color: "var(--color-text)",
                padding: "8px 16px",
                borderRadius: "6px",
                cursor: "pointer",
                fontWeight: "600",
                fontSize: "0.9rem",
                transition: "all 0.3s ease"
              }}
            >
              Continue as Guest
            </button>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Login;
