import { useEffect, useState } from "react";
import { fetchCart, updateCartQuantity, removeFromCart, clearCart } from "../api/cartApi";
import { useToast } from "../components/Toast";

function Cart({ onNavigateToHome }) {
  const [cartItems, setCartItems] = useState([]);
  const [loading, setLoading] = useState(true);
  const [checkoutMode, setCheckoutMode] = useState(false);
  const [shipping, setShipping] = useState({ name: "", address: "", phone: "", payment: "COD" });
  const { showToast } = useToast();

  const loadCart = async () => {
    try {
      setLoading(true);
      const res = await fetchCart();
      setCartItems(res.data);
    } catch (err) {
      showToast("Failed to retrieve cart items", "error");
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    loadCart();
  }, []);

  const handleQuantityChange = async (sweetId, currentQty, delta) => {
    const newQty = currentQty + delta;
    if (newQty <= 0) {
      handleRemove(sweetId);
      return;
    }
    try {
      await updateCartQuantity(sweetId, newQty);
      showToast("Cart updated successfully", "success");
      loadCart();
    } catch (err) {
      showToast("Failed to update item quantity", "error");
    }
  };

  const handleRemove = async (sweetId) => {
    try {
      await removeFromCart(sweetId);
      showToast("Item removed from cart", "info");
      loadCart();
    } catch (err) {
      showToast("Failed to remove item", "error");
    }
  };

  const handleCheckoutSubmit = async (e) => {
    e.preventDefault();
    try {
      await clearCart();
      showToast("🎉 Order placed successfully! Thank you for ordering.", "success");
      setCartItems([]);
      setCheckoutMode(false);
      if (onNavigateToHome) {
        setTimeout(onNavigateToHome, 2000);
      }
    } catch (err) {
      showToast("Failed to place order", "error");
    }
  };

  const subtotal = cartItems.reduce((acc, item) => acc + (item.sweet.price * item.quantity), 0);

  if (loading) {
    return <p style={{ textAlign: "center", padding: "60px", fontSize: "1.2rem", color: "#666" }}>Loading cart items...</p>;
  }

  return (
    <div className="dashboard-container">
      <div className="dashboard-header">
        <h2>Your Shopping Cart</h2>
        <button 
          onClick={onNavigateToHome} 
          style={{
            background: "none",
            border: "1.5px solid var(--color-primary)",
            color: "var(--color-primary)",
            padding: "8px 16px",
            borderRadius: "6px",
            fontWeight: "600",
            cursor: "pointer"
          }}
        >
          Back to Shop
        </button>
      </div>

      {cartItems.length === 0 ? (
        <div className="no-sweets-message" style={{ padding: "80px 20px" }}>
          <p style={{ fontSize: "1.3rem", fontWeight: "600", marginBottom: "15px", color: "var(--color-text-dark)" }}>
            🛒 Your cart is currently empty.
          </p>
          <button 
            onClick={onNavigateToHome}
            style={{
              padding: "10px 20px",
              backgroundColor: "var(--color-primary)",
              color: "white",
              borderRadius: "6px",
              fontWeight: "600",
              cursor: "pointer"
            }}
          >
            Explore Sweets
          </button>
        </div>
      ) : (
        <div className="cart-layout">
          {/* Cart items list */}
          <div className="cart-items-list">
            {cartItems.map((item) => (
              <div key={item.id} className="cart-item-row">
                <div className="cart-item-info">
                  <img src={item.sweet.imageUrl} alt={item.sweet.name} />
                  <div className="cart-item-details">
                    <h4>{item.sweet.name}</h4>
                    <p>₹{item.sweet.price} / kg</p>
                  </div>
                </div>

                <div className="cart-item-actions">
                  <div className="quantity-control" style={{ marginBottom: 0 }}>
                    <button 
                      className="quantity-btn"
                      onClick={() => handleQuantityChange(item.sweet.id, item.quantity, -1)}
                    >
                      -
                    </button>
                    <span className="quantity-value">{item.quantity}</span>
                    <button 
                      className="quantity-btn"
                      onClick={() => handleQuantityChange(item.sweet.id, item.quantity, 1)}
                    >
                      +
                    </button>
                  </div>
                  
                  <span style={{ fontWeight: "700", minWidth: "80px", textAlign: "right" }}>
                    ₹{item.sweet.price * item.quantity}
                  </span>

                  <button 
                    className="remove-item-btn"
                    onClick={() => handleRemove(item.sweet.id)}
                  >
                    Remove
                  </button>
                </div>
              </div>
            ))}
          </div>

          {/* Cart Summary */}
          <div className="cart-summary">
            <h3>Order Summary</h3>
            <div className="summary-row">
              <span>Items Total</span>
              <span>₹{subtotal}</span>
            </div>
            <div className="summary-row">
              <span>Delivery Charge</span>
              <span style={{ color: "var(--color-success)" }}>FREE</span>
            </div>
            <div className="summary-row total">
              <span>Subtotal</span>
              <span>₹{subtotal}</span>
            </div>

            {!checkoutMode ? (
              <button 
                className="checkout-btn"
                onClick={() => setCheckoutMode(true)}
              >
                Proceed to Checkout
              </button>
            ) : (
              <form onSubmit={handleCheckoutSubmit} className="checkout-form">
                <hr style={{ borderColor: "rgba(61, 64, 91, 0.1)", margin: "10px 0" }} />
                <h4>Delivery Details</h4>
                <input 
                  type="text" 
                  placeholder="Full Name"
                  value={shipping.name}
                  onChange={(e) => setShipping({ ...shipping, name: e.target.value })}
                  required
                />
                <input 
                  type="text" 
                  placeholder="Full Delivery Address"
                  value={shipping.address}
                  onChange={(e) => setShipping({ ...shipping, address: e.target.value })}
                  required
                />
                <input 
                  type="tel" 
                  placeholder="Phone Number"
                  value={shipping.phone}
                  onChange={(e) => setShipping({ ...shipping, phone: e.target.value })}
                  required
                />
                <select 
                  value={shipping.payment}
                  onChange={(e) => setShipping({ ...shipping, payment: e.target.value })}
                  style={{
                    padding: "10px 14px",
                    border: "1px solid #dcdcdc",
                    borderRadius: "6px",
                    fontSize: "0.9rem",
                    outline: "none",
                    background: "white"
                  }}
                >
                  <option value="COD">Cash on Delivery (COD)</option>
                  <option value="UPI">Pay via UPI / Card (Mock)</option>
                </select>

                <div style={{ display: "flex", gap: "10px", marginTop: "10px" }}>
                  <button 
                    type="submit" 
                    className="checkout-btn" 
                    style={{ margin: 0, flex: 1 }}
                  >
                    Place Order (₹{subtotal})
                  </button>
                  <button 
                    type="button" 
                    onClick={() => setCheckoutMode(false)}
                    style={{
                      background: "none",
                      border: "1.5px solid rgba(61, 64, 91, 0.2)",
                      color: "var(--color-text-dark)",
                      borderRadius: "6px",
                      padding: "12px",
                      fontWeight: "600",
                      cursor: "pointer"
                    }}
                  >
                    Cancel
                  </button>
                </div>
              </form>
            )}
          </div>
        </div>
      )}
    </div>
  );
}

export default Cart;
