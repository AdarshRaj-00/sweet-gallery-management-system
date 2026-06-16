import React, { useState } from "react";
import { getUsernameFromToken, getRoleFromToken } from "../utils/jwt";
import { useToast } from "./Toast";

function Navbar({ 
  searchQuery, 
  setSearchQuery, 
  activeCategory, 
  setActiveCategory, 
  cartCount, 
  wishlistCount,
  currentView,
  setCurrentView,
  onLogout,
  onNavigateToLogin,
  onNavigateToRegister
}) {
  const username = getUsernameFromToken();
  const role = getRoleFromToken();
  const isLoggedIn = !!username;
  const { showToast } = useToast();

  const categoriesList = [
    { label: "All", value: "All" },
    { label: "Milk Sweets", value: "Milk Sweet" },
    { label: "Bengali Sweets", value: "Bengali Sweet" },
    { label: "Dry Fruits", value: "Dry fruit" },
    { label: "Festival Sweets", value: "Festival Sweet" }
  ];

  return (
    <header className="navbar">
      <div className="nav-top">
        <div className="brand" onClick={() => setCurrentView("home")} style={{ cursor: "pointer" }}>
          <h2>Sweet Gallery</h2>
          <span className="tagline">Fresh & Authentic Indian Sweets</span>
        </div>

        <nav style={{ display: "flex", gap: "25px", fontWeight: "600", fontSize: "0.95rem" }}>
          <span 
            onClick={() => setCurrentView("home")} 
            style={{ 
              color: currentView === "home" ? "var(--color-accent)" : "white", 
              cursor: "pointer",
              transition: "color 0.2s"
            }}
          >
            Sweets
          </span>
          <span style={{ color: "rgba(255,255,255,0.6)", cursor: "not-allowed" }}>About</span>
          <span style={{ color: "rgba(255,255,255,0.6)", cursor: "not-allowed" }}>Contact</span>
        </nav>

        <div className="nav-actions">
          <div 
            className="wishlist-indicator" 
            style={{ position: "relative", cursor: "pointer", fontSize: "1.1rem" }}
          >
            ❤️ <span style={{ fontSize: "0.85rem", fontWeight: "700" }}>Wishlist ({wishlistCount})</span>
          </div>

          <div 
            onClick={() => {
              if (isLoggedIn) {
                setCurrentView("cart");
              } else {
                showToast && showToast("Please login to view your cart", "error");
                onNavigateToLogin();
              }
            }} 
            className="cart-indicator"
            style={{ 
              position: "relative", 
              cursor: "pointer", 
              background: "rgba(255, 255, 255, 0.1)",
              padding: "6px 12px",
              borderRadius: "20px",
              border: currentView === "cart" ? "1px solid var(--color-accent)" : "1px solid rgba(255,255,255,0.2)",
              display: "flex",
              alignItems: "center",
              gap: "6px"
            }}
          >
            <span>🛒</span>
            <span style={{ fontWeight: "700", color: currentView === "cart" ? "var(--color-accent)" : "white" }}>
              Cart ({cartCount})
            </span>
          </div>

          <div className="user-profile-nav" style={{ marginLeft: "10px" }}>
            {isLoggedIn ? (
              <div style={{ display: "flex", alignItems: "center", gap: "12px" }}>
                <span style={{ fontSize: "0.9rem", color: "var(--color-accent)" }}>
                  👤 Hi, <strong>{username}</strong> {role === "ADMIN" && <span style={{ fontSize: "0.75rem", background: "red", color: "white", padding: "2px 4px", borderRadius: "3px" }}>Admin</span>}
                </span>
                <button onClick={onLogout} className="logout-btn" style={{ padding: "6px 12px" }}>
                  Logout
                </button>
              </div>
            ) : (
              <div style={{ display: "flex", gap: "10px" }}>
                <button 
                  onClick={onNavigateToLogin} 
                  style={{
                    background: "transparent",
                    border: "1px solid var(--color-accent)",
                    color: "var(--color-accent)",
                    padding: "6px 12px",
                    borderRadius: "6px",
                    fontSize: "0.85rem",
                    fontWeight: "600",
                    cursor: "pointer"
                  }}
                >
                  Login
                </button>
                <button 
                  onClick={onNavigateToRegister}
                  style={{
                    backgroundColor: "var(--color-primary)",
                    border: "none",
                    color: "white",
                    padding: "6px 12px",
                    borderRadius: "6px",
                    fontSize: "0.85rem",
                    fontWeight: "600",
                    cursor: "pointer"
                  }}
                >
                  Register
                </button>
              </div>
            )}
          </div>
        </div>
      </div>

      {currentView === "home" && (
        <>
          <div className="search-bar">
            <input
              type="text"
              placeholder="Search sweets or categories..."
              value={searchQuery}
              onChange={(e) => setSearchQuery(e.target.value)}
            />
          </div>

          <div className="categories">
            {categoriesList.map((cat) => (
              <button
                key={cat.value}
                className={activeCategory === cat.value ? "active" : ""}
                onClick={() => setActiveCategory(cat.value)}
              >
                {cat.label}
              </button>
            ))}
          </div>
        </>
      )}
    </header>
  );
}

export default Navbar;