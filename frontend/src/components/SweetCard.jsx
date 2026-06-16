import React, { useState } from "react";
import { deleteSweet } from "../api/sweetsApi";
import { getUsernameFromToken } from "../utils/jwt";
import { useToast } from "./Toast";

function SweetCard({ sweet, isAdmin, onPurchaseSuccess, onAddToCart, onWishlistToggle, isWishlisted, onLoginRequired }) {
  const [loading, setLoading] = useState(false);
  const [quantity, setQuantity] = useState(1);
  const isOutOfStock = sweet.quantity === 0;
  const username = getUsernameFromToken();
  const isLoggedIn = !!username;
  const { showToast } = useToast();

  const rating = (4.2 + (sweet.id % 8) * 0.1).toFixed(1);
  const reviewsCount = 50 + (sweet.id * 17) % 320;
  
  const discountPercent = 10 + (sweet.id * 5) % 20;
  const badgeText = sweet.id % 3 === 0 ? "Best Seller" : sweet.id % 3 === 1 ? "New" : `${discountPercent}% OFF`;
  const badgeColor = badgeText.includes("OFF") ? "#e07a5f" : badgeText === "New" ? "#81b29a" : "#2b2d42";

  const handleAddToCartClick = async () => {
    if (!isLoggedIn) {
      showToast("Please login to add items to your cart", "error");
      if (onLoginRequired) onLoginRequired();
      return;
    }
    try {
      setLoading(true);
      if (onAddToCart) {
        await onAddToCart(sweet.id, quantity);
      }
      setQuantity(1);
    } catch (err) {
      showToast("Failed to add sweet to cart", "error");
    } finally {
      setLoading(false);
    }
  };

  const handleWishlistClick = () => {
    if (!isLoggedIn) {
      showToast("Please login to wishlist items", "error");
      if (onLoginRequired) onLoginRequired();
      return;
    }
    if (onWishlistToggle) {
      onWishlistToggle(sweet.id);
    }
  };

  const handleDelete = async () => {
    if (!window.confirm(`Are you sure you want to delete "${sweet.name}"?`)) return;
    try {
      setLoading(true);
      await deleteSweet(sweet.id);
      showToast("Sweet deleted successfully", "info");
      if (onPurchaseSuccess) {
        onPurchaseSuccess();
      }
    } catch (error) {
      showToast("Failed to delete sweet: " + (error.response?.data || error.message), "error");
    } finally {
      setLoading(false);
    }
  };

  return (
    <div className="sweet-card" style={{ position: "relative" }}>
      <div 
        className={`wishlist-heart ${isWishlisted ? "active" : ""}`}
        onClick={handleWishlistClick}
      >
        {isWishlisted ? "♥" : "♡"}
      </div>

      <div 
        className="discount-badge"
        style={{
          position: "absolute",
          top: "15px",
          left: "15px",
          padding: "4px 10px",
          borderRadius: "6px",
          fontSize: "0.75rem",
          fontWeight: "700",
          color: "white",
          zIndex: "2",
          backgroundColor: badgeColor
        }}
      >
        {badgeText}
      </div>

      <img src={sweet.imageUrl} alt={sweet.name} />

      <div className="sweet-info">
        <p className="category">{sweet.category}</p>
        <h3>{sweet.name}</h3>

        {/* Ratings display */}
        <div style={{ display: "flex", alignItems: "center", gap: "5px", margin: "4px 0 10px 0", fontSize: "0.85rem", color: "#666" }}>
          <span style={{ color: "var(--color-accent)", fontSize: "1.1rem" }}>★</span>
          <strong>{rating}</strong>
          <span>({reviewsCount} Reviews)</span>
        </div>

        <p className="price">₹{sweet.price} / kg</p>
        <p className="stock" style={{ color: isOutOfStock ? "var(--color-danger)" : "#666" }}>
          {isOutOfStock ? "Out of Stock" : `Available: ${sweet.quantity} kg`}
        </p>

        <div style={{ display: "flex", flexDirection: "column", gap: "8px", marginTop: "auto" }}>
          {!isAdmin && !isOutOfStock && (
            <div className="quantity-control">
              <button 
                className="quantity-btn"
                onClick={() => setQuantity(q => Math.max(1, q - 1))}
                disabled={quantity <= 1 || loading}
              >
                -
              </button>
              <span className="quantity-value">{quantity}</span>
              <button 
                className="quantity-btn"
                onClick={() => setQuantity(q => Math.min(sweet.quantity, q + 1))}
                disabled={quantity >= sweet.quantity || loading}
              >
                +
              </button>
            </div>
          )}

          {!isAdmin && (
            <button
              disabled={isOutOfStock || loading}
              onClick={handleAddToCartClick}
            >
              {loading ? "Adding..." : isOutOfStock ? "Unavailable" : "+ Add to Cart"}
            </button>
          )}

          {isAdmin && (
            <button
              disabled={loading}
              onClick={handleDelete}
              style={{
                backgroundColor: "#dc3545",
                color: "white"
              }}
            >
              {loading ? "Deleting..." : "Delete Sweet"}
            </button>
          )}
        </div>
      </div>
    </div>
  );
}

export default SweetCard;