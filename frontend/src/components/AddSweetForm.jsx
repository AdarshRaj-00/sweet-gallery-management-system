import { useState } from "react";
import { addSweet } from "../api/sweetsApi";

function AddSweetForm({ onAddSuccess }) {
  const [form, setForm] = useState({
    name: "",
    category: "",
    price: "",
    quantity: "",
    imageUrl: ""
  });

  const [error, setError] = useState(null);
  const [loading, setLoading] = useState(false);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
    setError(null);
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    try {
      setLoading(true);
      await addSweet({
        ...form,
        price: Number(form.price),
        quantity: Number(form.quantity)
      });
      setForm({ name: "", category: "", price: "", quantity: "", imageUrl: "" });
      if (onAddSuccess) {
        onAddSuccess();
      }
    } catch (err) {
      setError("Failed to add sweet: " + (err.response?.data || err.message));
    } finally {
      setLoading(false);
    }
  };

  return (
    <form className="admin-form" onSubmit={handleSubmit} style={{ display: "flex", flexDirection: "column", gap: "15px" }}>
      <h3>Add New Sweet</h3>
      {error && <p style={{ color: "var(--color-danger)", fontWeight: "600", fontSize: "0.9rem" }}>{error}</p>}
      
      <div style={{ display: "grid", gridTemplateColumns: "repeat(auto-fit, minmax(200px, 1fr))", gap: "15px" }}>
        <input
          name="name"
          placeholder="Sweet Name"
          value={form.name}
          onChange={handleChange}
          required
        />
        
        <input
          name="category"
          placeholder="Category (e.g., Milk Sweet)"
          value={form.category}
          onChange={handleChange}
          required
        />
        
        <input
          name="price"
          type="number"
          step="0.01"
          placeholder="Price"
          value={form.price}
          onChange={handleChange}
          required
        />
        
        <input
          name="quantity"
          type="number"
          placeholder="Quantity"
          value={form.quantity}
          onChange={handleChange}
          required
        />
      </div>

      <input
        name="imageUrl"
        placeholder="Image URL"
        value={form.imageUrl}
        onChange={handleChange}
        required
      />
      
      <button type="submit" disabled={loading}>
        {loading ? "Adding..." : "Add Sweet to Catalog"}
      </button>
    </form>
  );
}

export default AddSweetForm;
