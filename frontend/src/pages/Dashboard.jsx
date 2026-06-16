import React, { useEffect, useState } from "react";
import SweetCard from "../components/SweetCard";
import AddSweetForm from "../components/AddSweetForm";
import SkeletonCard from "../components/SkeletonCard";
import { fetchAllSweets } from "../api/sweetsApi";
import { getRoleFromToken, clearToken } from "../utils/jwt";

function Dashboard({ 
  onLogout, 
  searchQuery, 
  activeCategory, 
  onAddToCart, 
  wishlist = [], 
  onWishlistToggle, 
  onLoginRequired 
}) {
  const [sweets, setSweets] = useState([]);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState(null);
  const role = getRoleFromToken();
  const isAdmin = role === "ADMIN";

  const loadSweets = () => {
    fetchAllSweets()
      .then((response) => {
        setSweets(response.data);
        setLoading(false);
      })
      .catch(() => {
        setError("Failed to load sweets");
        setLoading(false);
      });
  };

  useEffect(() => {
    loadSweets();
  }, []);

  const handlePurchaseSuccess = () => {
    loadSweets();
  };

  const handleLogout = () => {
    clearToken();
    if (onLogout) {
      onLogout();
    }
  };

  if (loading) {
    return (
      <div className="dashboard-container">
        <div className="dashboard-header">
          <h2>Sweet Catalog</h2>
        </div>
        <div className="dashboard">
          {Array.from({ length: 6 }).map((_, idx) => (
            <SkeletonCard key={idx} />
          ))}
        </div>
      </div>
    );
  }

  if (error) {
    return <p style={{ textAlign: "center", color: "red", padding: "40px", fontSize: "1.2rem" }}>{error}</p>;
  }

  const filteredSweets = sweets.filter((sweet) => {
    const matchesSearch =
      sweet.name.toLowerCase().includes(searchQuery.toLowerCase()) ||
      sweet.category.toLowerCase().includes(searchQuery.toLowerCase());
    const matchesCategory =
      activeCategory === "All" ||
      sweet.category.toLowerCase().includes(activeCategory.toLowerCase());
    return matchesSearch && matchesCategory;
  });

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h2>Sweet Catalog</h2>
        <div className="user-profile-nav">
          <p className="role-indicator">Logged in as: <strong>{role || "GUEST"}</strong></p>
          <button onClick={handleLogout} className="logout-btn">
            Logout
          </button>
        </div>
      </div>
      
      {isAdmin && <AddSweetForm onAddSuccess={handlePurchaseSuccess} />}
      
      {sweets.length === 0 ? (
        <div className="no-sweets-message">
          <p>No sweets available in the inventory.</p>
        </div>
      ) : filteredSweets.length === 0 ? (
        <div className="no-sweets-message">
          <p>No sweets match your search or filter criteria.</p>
        </div>
      ) : (
        <div className="dashboard">
          {filteredSweets.map((sweet) => (
            <SweetCard 
              key={sweet.id} 
              sweet={sweet} 
              isAdmin={isAdmin}
              onPurchaseSuccess={handlePurchaseSuccess} 
              onAddToCart={onAddToCart}
              isWishlisted={wishlist.includes(sweet.id)}
              onWishlistToggle={onWishlistToggle}
              onLoginRequired={onLoginRequired}
            />
          ))}
        </div>
      )}
    </div>
  );
}

export default Dashboard;
