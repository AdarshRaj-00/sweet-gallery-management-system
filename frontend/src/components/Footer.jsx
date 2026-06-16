import React from "react";

function Footer() {
  return (
    <footer className="footer">
      <div className="footer-content">
        <div className="footer-section">
          <h3>Sweet Gallery</h3>
          <p style={{ marginTop: "10px" }}>
            Handcrafted with love. We bring you the authentic taste of traditional Indian sweets, prepared with high-quality ingredients and delivered fresh to your doorstep.
          </p>
        </div>
        
        <div className="footer-section">
          <h4>Quick Links</h4>
          <ul>
            <li><a>Home / Sweets</a></li>
            <li><a>About Us</a></li>
            <li><a>Our Story</a></li>
            <li><a>Contact Support</a></li>
          </ul>
        </div>
        
        <div className="footer-section">
          <h4>Policy</h4>
          <ul>
            <li><a>Privacy Policy</a></li>
            <li><a>Terms of Service</a></li>
            <li><a>Refund Policy</a></li>
            <li><a>Shipping Details</a></li>
          </ul>
        </div>
      </div>
      
      <div className="footer-bottom">
        <p>© 2025 Sweet Gallery. All rights reserved.</p>
        <p>Made with ❤️ by Indian Sweet Lovers</p>
      </div>
    </footer>
  );
}

export default Footer;
