import React, { useState, useEffect } from "react";
import Dashboard from "./pages/Dashboard";
import Login from "./pages/Login";
import Register from "./pages/Register";
import Cart from "./pages/Cart";
import Navbar from "./components/Navbar";
import Footer from "./components/Footer";
import { getTokenFromStorage, clearToken } from "./utils/jwt";
import { fetchCart, addToCart } from "./api/cartApi";
import { useToast } from "./components/Toast";

function App() {
  const [currentView, setCurrentView] = useState("home"); // "home", "cart", "login", "register"
  const [isAuthenticated, setIsAuthenticated] = useState(false);
  const [searchQuery, setSearchQuery] = useState("");
  const [activeCategory, setActiveCategory] = useState("All");
  
  // E-commerce state
  const [cartCount, setCartCount] = useState(0);
  const [wishlist, setWishlist] = useState([]);
  const { showToast } = useToast();

  const loadCartCount = async () => {
    const token = getTokenFromStorage();
    if (!token) {
      setCartCount(0);
      return;
    }
    try {
      const res = await fetchCart();
      const count = res.data.reduce((acc, item) => acc + item.quantity, 0);
      setCartCount(count);
    } catch (err) {
      console.error("Failed to load cart count", err);
    }
  };

  useEffect(() => {
    const token = getTokenFromStorage();
    const isAuth = !!token;
    setIsAuthenticated(isAuth);
    if (isAuth) {
      loadCartCount();
    } else {
      // Direct guests to login page initially so they can choose to login or continue as guest
      setCurrentView("login");
    }
  }, []);

  const handleLoginSuccess = () => {
    setIsAuthenticated(true);
    setCurrentView("home");
    loadCartCount();
    showToast("✓ Welcome back! Login successful.", "success");
  };

  const handleLogout = () => {
    clearToken();
    setIsAuthenticated(false);
    setCartCount(0);
    setWishlist([]);
    setSearchQuery("");
    setActiveCategory("All");
    setCurrentView("login");
    showToast("✓ Logged out successfully", "info");
  };

  const handleAddToCart = async (sweetId, qty) => {
    try {
      await addToCart(sweetId, qty);
      showToast("✓ Item added to cart!", "success");
      loadCartCount();
    } catch (err) {
      showToast("Failed to add item to cart", "error");
    }
  };

  const handleWishlistToggle = (sweetId) => {
    setWishlist((prev) => {
      const isFav = prev.includes(sweetId);
      if (isFav) {
        showToast("Removed from wishlist", "info");
        return prev.filter((id) => id !== sweetId);
      } else {
        showToast("❤️ Added to wishlist!", "success");
        return [...prev, sweetId];
      }
    });
  };

  // Rendering screen flow
  const renderView = () => {
    if (currentView === "login") {
      return (
        <Login 
          onLogin={handleLoginSuccess} 
          onNavigateToRegister={() => setCurrentView("register")}
          onContinueAsGuest={() => {
            setCurrentView("home");
            showToast("Browsing as guest. Please login to purchase items.", "info");
          }}
        />
      );
    }

    if (currentView === "register") {
      return (
        <Register onNavigateToLogin={() => setCurrentView("login")} />
      );
    }

    return (
      <div className="app-container">
        <Navbar 
          searchQuery={searchQuery} 
          setSearchQuery={setSearchQuery} 
          activeCategory={activeCategory} 
          setActiveCategory={setActiveCategory}
          cartCount={cartCount}
          wishlistCount={wishlist.length}
          currentView={currentView}
          setCurrentView={setCurrentView}
          onLogout={handleLogout}
          onNavigateToLogin={() => setCurrentView("login")}
          onNavigateToRegister={() => setCurrentView("register")}
        />
        <main style={{ flex: 1 }}>
          {currentView === "home" ? (
            <Dashboard 
              searchQuery={searchQuery} 
              activeCategory={activeCategory} 
              onAddToCart={handleAddToCart}
              wishlist={wishlist}
              onWishlistToggle={handleWishlistToggle}
              onLoginRequired={() => setCurrentView("login")}
            />
          ) : (
            <Cart onNavigateToHome={() => {
              setCurrentView("home");
              loadCartCount();
            }} />
          )}
        </main>
        <Footer />
      </div>
    );
  };

  return <>{renderView()}</>;
}

export default App;
